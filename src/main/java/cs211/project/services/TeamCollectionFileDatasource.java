package cs211.project.services;

import cs211.project.models.Team;
import cs211.project.models.collections.UserTeamCollection;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TeamCollectionFileDatasource implements Datasource<UserTeamCollection> {

    private String directoryName;
    private String fileName;

    public TeamCollectionFileDatasource(String directoryName, String fileName) {
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
    public UserTeamCollection readData() {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        UserTeamCollection userTeamCollection = new UserTeamCollection(); // Create a new UserTeamCollection to hold the data

        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) { // Ensure there are enough parts to create a Team
                    String teamName = parts[0].trim();
                    String maxMember = parts[1].trim();
                    String startDate = parts[2].trim();
                    String dueDate = parts[3].trim();
                    String eventName = parts[4].trim();
                    userTeamCollection.addNewTeam(teamName, maxMember, startDate, dueDate,eventName);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return userTeamCollection;
    }


    @Override
    public void writeData(UserTeamCollection data) {
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

            for (Team team : data.getTeam()) {
                String line = team.getTeamName() + "," + team.getMaxMember() + "," + team.getStartDate()+ "," + team.getDueDate()+ "," + team.getEventName();
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
