package com.test400.site.services;

import com.test400.site.models.*;
import com.test400.site.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class XMLParser {

    private SplitRepository splitRepository;
    private SwimstyleRepository swimstyleRepository;
    private MeetRepository meetRepository;
    private AthleteRepository athleteRepository;
    private ResultRepository resultRepository;
    private EventRepository eventRepository;

    @Autowired
    public XMLParser(MeetRepository meetRepository,
                     AthleteRepository athleteRepository,
                     ResultRepository resultRepository,
                     SwimstyleRepository swimstyleRepository,
                     EventRepository eventRepository,
                     SplitRepository splitRepository
    ){
        this.meetRepository = meetRepository;
        this.athleteRepository = athleteRepository;
        this.resultRepository = resultRepository;
        this.swimstyleRepository = swimstyleRepository;
        this.eventRepository = eventRepository;
        this.splitRepository = splitRepository;
    }

    private static File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }

    void createMeetFromFile(MultipartFile multipartFile){

        try {
//            File xmlFile = new File("C:/Users/Administrator/Desktop/test.lef");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(convert(multipartFile));

            doc.getDocumentElement().normalize();

            this.addMeet(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMeet (Document doc) {
        NodeList meetList = doc.getElementsByTagName("MEET");
        Node meetNode = meetList.item(0);

        NodeList datumList = doc.getElementsByTagName("AGEDATE");
        Node datumNode = datumList.item(0);

        String meetName = ((Element)meetNode).getAttribute("name");
        String meetCity = ((Element)meetNode).getAttribute("city");
        String meetDate = ((Element)datumNode).getAttribute("value");
        String meetCourse = ((Element)datumNode).getAttribute("course");

        Meet meet = new Meet();
        meet.setName(meetName);
        meet.setCity(meetCity);
        meet.setDate(meetDate);
        meet.setCourse(meetCourse);

        Meet meet1 = meetRepository.save(meet);

        meet1 = this.addEventsAndSwimstyles(doc, meet1);
        meet1 = this.addAthletesAndResults(doc, meet1);
//        this.addRelays(doc, meet1);
    }

//    private void addRelays(Document doc, Meet meet1) {
//        NodeList relaysNodeList = doc.getElementsByTagName("RELAY");
//
//    }

    private Meet addEventsAndSwimstyles(Document doc, Meet meet) {
        NodeList eventNodeList = doc.getElementsByTagName("EVENT");
        List<Event> eventList= new ArrayList<>();
        for (int i = 0; i < eventNodeList.getLength(); i++){
            Node eventNode = eventNodeList.item(i);
            if(eventNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eventElement = (Element) eventNode;
                Swimstyle swimstyle = addSwimstyle(doc, i, meet);
                Event event = eventRepository.save(createEvent(eventElement, meet, swimstyle));
                eventList.add(event);
            }
        }
        meet.setEvents(eventList);
        return meetRepository.save(meet);
    }

    private Swimstyle addSwimstyle(Document doc, int i, Meet meet) {
        NodeList eventNodeList = doc.getElementsByTagName("EVENT");
        NodeList swimstyleNodeList = doc.getElementsByTagName("SWIMSTYLE");
        Node eventNode = eventNodeList.item(i);
        Node swimstyleNode = swimstyleNodeList.item(i);

        String gender = "";
        Swimstyle swimstyle = null;

        if(eventNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eventElement = (Element) eventNode;
            gender = eventElement.getAttribute("gender");
        }

        if(swimstyleNode.getNodeType() == Node.ELEMENT_NODE) {
            Element swimstyleElement = (Element) swimstyleNode;
                    swimstyle = swimstyleRepository.findSwimstyleByDistanceAndStrokeAndGender(
                    swimstyleElement.getAttribute("distance"),
                    swimstyleElement.getAttribute("stroke"),
                    gender
            );

            if (swimstyle == null) {
                swimstyle = createSwimstyle(swimstyleElement, gender);
            }
        }
        assert swimstyle != null;
        swimstyle.getMeets().add(meet);
        return swimstyleRepository.save(swimstyle);
    }

    private Swimstyle findSwimstyle(Document doc, String eventid, Meet meet) {

        String swimdistance = "";
        String swimstroke = "";
        String gender = "";

        try {
            Event event = eventRepository.findByEventidAndMeet(eventid, meet);
            gender = event.getGender();
            swimdistance = event.getSwimstyle().getDistance();
            swimstroke = event.getSwimstyle().getStroke();
        } catch (Exception e){
            System.out.println(e);
        }
        return swimstyleRepository.findSwimstyleByDistanceAndStrokeAndGender(swimdistance, swimstroke, gender);
    }

    private Meet addAthletesAndResults(Document doc, Meet meet) {
        NodeList athletes = doc.getElementsByTagName("ATHLETE");
        List<Athlete> athletesList = new ArrayList();
        for (int i = 0; i < athletes.getLength(); i++){
            Node athleteNode = athletes.item(i);
            if(athleteNode.getNodeType() == Node.ELEMENT_NODE){
                Element athleteElement = (Element) athleteNode;
                Athlete athlete = athleteRepository.findByLicense(athleteElement.getAttribute("license"));
                if (athlete == null) {
                    athlete = athleteRepository.save(createAthlete(athleteElement));
                }
                athletesList.add(athlete);

                this.addResults(doc, athleteElement.getElementsByTagName("RESULT"), athlete, meet);
            }
        }
        meet.setAthletes(athletesList);
        return meetRepository.save(meet);
    }

    private void addResults(Document doc, NodeList results, Athlete athlete, Meet meet) {

        for (int j = 0; j < results.getLength(); j++){
            Node resultNode = results.item(j);
            if(resultNode.getNodeType() == Node.ELEMENT_NODE){
                Element resultElement = (Element) resultNode;
                Swimstyle swimstyle = findSwimstyle(doc, resultElement.getAttribute("eventid"), meet);
                swimstyle.getAthletes().add(athlete);
                NodeList splits = resultElement.getElementsByTagName("SPLIT");
                List<Split> splitsArray = new ArrayList<>();
                for (int i = 0; i < splits.getLength(); i++){
                    Node splitNode = splits.item(i);
                    if(splitNode.getNodeType() == Node.ELEMENT_NODE){
                        Element splitElement = (Element) splitNode;
                        splitsArray.add(splitRepository.save(createSplit(
                                splitElement
                                ))
                        );
                    }
                }
                resultRepository.save(createResult(resultElement, athlete, meet, swimstyle, splitsArray));
            }
        }
    }

    private Result createResult(Element eElement, Athlete athlete, Meet meet, Swimstyle swimstyle, List<Split> splits) {
        Result res = new Result();
        res.setAthlete(athlete);
        LocalTime swimtimeDF = LocalTime.parse(eElement.getAttribute("swimtime"), DateTimeFormatter.ofPattern("HH:mm:ss.SS"));
        Long longTime = (swimtimeDF.getHour() * 60 * 60 * 100) +
                (swimtimeDF.getMinute() * 60 * 100) +
                (swimtimeDF.getSecond() * 100) +
                (swimtimeDF.getLong(ChronoField.MILLI_OF_SECOND) / 10);
        res.setSwimtime(longTime);
        res.setMeet(meet);
        res.setSwimstyle(swimstyle);
        res.setSplits(splits);
        return res;
    }

    private Athlete createAthlete(Element eElement) {
        return new Athlete(
                eElement.getAttribute("athleteid"),
                eElement.getAttribute("firstname"),
                eElement.getAttribute("lastname"),
                eElement.getAttribute("gender"),
                eElement.getAttribute("license"),
                eElement.getAttribute("birthdate")
        );
    }

    private Swimstyle createSwimstyle(Element swimstyleElement, String gender) {
        return new Swimstyle(
                swimstyleElement.getAttribute("distance"),
                swimstyleElement.getAttribute("stroke"),
                gender
        );
    }

    private Event createEvent(Element eventElement, Meet meet, Swimstyle swimstyle) {
        return new Event(
                eventElement.getAttribute("eventid"),
                eventElement.getAttribute("gender"),
                eventElement.getAttribute("number"),
                meet,
                swimstyle
        );
    }

    private Split createSplit(Element eElement) {
        return new Split(
                eElement.getAttribute("swimtime"),
                eElement.getAttribute("distance")
        );
    }
}
