package cs211.project.services;
import cs211.project.models.Event;
import cs211.project.models.collections.EventCollection;

import java.io.*;
import java.nio.charset.StandardCharsets;
public class EventCollectionFileDatasource implements Datasource<EventCollection>{
    private String directoryName;
    private String fileName;

    public EventCollectionFileDatasource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }
    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public EventCollection readData() {
        EventCollection events = new EventCollection();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        // เตรียม object ที่ใช้ในการอ่านไฟล์
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        InputStreamReader inputStreamReader = new InputStreamReader(
                fileInputStream,
                StandardCharsets.UTF_8
        );
        BufferedReader buffer = new BufferedReader(inputStreamReader);

        String line = "";
        try {
            while ( (line = buffer.readLine()) != null ){
                if (line.equals("")) continue;
                String[] data = line.split(",");

                // อ่านข้อมูลตาม index แล้วจัดการประเภทของข้อมูลให้เหมาะสม
                String eventName = data[0].trim();
                String eventDetail = data[1].trim();
                int seatFull = Integer.parseInt(data[2].trim());
                String startDate = data[3].trim();
                String dueDate = data[4].trim();
                String creatorUsername = data[5].trim();
                String eventPic = data[6].trim();

                // เพิ่มข้อมูลลงใน list
                events.addNewEvent(eventName,eventDetail,seatFull,startDate,dueDate,
                        creatorUsername,eventPic);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return events;
    }

    @Override
    public void writeData(EventCollection data) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        // เตรียม object ที่ใช้ในการเขียนไฟล์
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                fileOutputStream,
                StandardCharsets.UTF_8
        );
        BufferedWriter buffer = new BufferedWriter(outputStreamWriter);

        try {
            for (Event event : data.getEvents()) {
                String line = event.getEventName() + "," + event.getEventDetail() +
                        "," + event.getSeatFull() + "," + event.getStartDate() +
                        "," + event.getDueDate() + "," + event.getCreatorUsername()
                        + "," + event.getEventPic();
                buffer.append(line);
                buffer.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.flush();
                buffer.close();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}

