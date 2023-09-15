package cs211.project.services;
        import cs211.project.models.Activity;
        import cs211.project.models.Event;
        import cs211.project.models.collections.ActivityCollection;

        import java.io.*;
        import java.nio.charset.StandardCharsets;
public class ActivityCollectionFileDatasource implements Datasource<ActivityCollection>{
    private String directoryName;
    private String fileName;

    public ActivityCollectionFileDatasource (String directoryName, String fileName) {
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
    public ActivityCollection readData() {
        ActivityCollection activityCollection = new ActivityCollection();
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
                String activityName = data[0].trim();
                String detail = data[1].trim();
                String startDate = data[2].trim();
                String dueDate = data[3].trim();
                String eventName = data[4].trim();
                String teamName = data[5].trim();

                // เพิ่มข้อมูลลงใน list
                activityCollection.addActivity(activityName, detail, startDate, dueDate, eventName, teamName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return activityCollection;
    }

    @Override
    public void writeData(ActivityCollection data) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        // เตรียม object ที่ใช้ในการเขียนไฟล์
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file,false);
            // แก้เป็น false ถึงจะไปแก้อันเก่า
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                fileOutputStream,
                StandardCharsets.UTF_8
        );
        BufferedWriter buffer = new BufferedWriter(outputStreamWriter);

        try {
            for (Activity activity : data.getActivities()) {
                String line =  activity.getActivityName() +
                        "," + activity.getActivityDetail() +
                        "," + activity.getActivityStartDate() +
                        "," + activity.getActivityDueDate() +
                        "," + activity.getEventName() +
                        "," + activity.getTeamName();

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
