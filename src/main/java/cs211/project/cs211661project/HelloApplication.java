package cs211.project.cs211661project;

import javafx.application.Application;
import javafx.stage.Stage;
import cs211.project.services.FXRouter;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        configRoute();

        FXRouter.bind(this, stage, "CS211 661 Project");
        FXRouter.goTo("mainPage");
    }

    private static void configRoute() {
        String resourcesPath = "cs211/project/views/";
        FXRouter.when("amdin", resourcesPath + "admin-profile.fxml");
        FXRouter.when("hello", resourcesPath + "hello-view.fxml");
        FXRouter.when("member", resourcesPath + "list-of-member.fxml");
        FXRouter.when("listteams", resourcesPath + "list-of-teams.fxml");
        FXRouter.when("createAccount", resourcesPath + "create-account-view.fxml");
        FXRouter.when("createEvent", resourcesPath + "create-event.fxml");
        FXRouter.when("myProfile", resourcesPath + "my-profile-view.fxml");
        FXRouter.when("resetPassword", resourcesPath + "reset-password-view.fxml");
        FXRouter.when("conquerorMembers", resourcesPath + "conqueror-members.fxml");
        FXRouter.when("mainPage", resourcesPath + "main-page.fxml");
        FXRouter.when("logIn", resourcesPath + "login.fxml");
        FXRouter.when("createTeam", resourcesPath + "create-team.fxml");
        FXRouter.when("editTeamAct", resourcesPath + "edit-team-activities.fxml");
        FXRouter.when("team", resourcesPath + "team.fxml");

        FXRouter.when("searchEvent", resourcesPath + "search-event.fxml");
        FXRouter.when("editEvent", resourcesPath + "edit-event.fxml");
        FXRouter.when("creatorEvent", resourcesPath + "creator-event.fxml");
        FXRouter.when("userEvent", resourcesPath + "user-event.fxml");
        FXRouter.when("userActivity", resourcesPath + "user-activity.fxml");

        FXRouter.when("editActivities", resourcesPath + "edit-activities.fxml");
        FXRouter.when("joinTeam", resourcesPath + "join-team.fxml");
    }


    public static void main(String[] args) {
        launch();
    }
}