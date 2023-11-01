import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class FormUsers implements Serializable {
    Stage stage;

    ListView <User> myUsers;
    Button btnNew, btnEdit, btnDelete;
    Button btnSave, btnCancel2;

    Label lbLastname, lbFirstname, lbUsername,lbPassword, lbrole;
    TextField txtLastname, txtFirstname, txtUsername,txtPassword;
    ComboBox <String> comrole;

    ArrayList <User> userList;

    enumUserState enumState;
    HBox paneSaveCancel, hBox;

    File f = new File("data.data");

    User rafail, nikos;
    public FormUsers() {
//        rafail = new User("Balis", "Rafail", "Rafimc13", "1314", "0");
//        nikos = new User("Balis", "Nikos", "NikPanther", "1252", "1");
//        userList = new ArrayList<>(10);
//        userList.add(0,rafail);
//        userList.add(1, nikos);


        show();
    }

    public void show () {

        userList = new ArrayList<>(10);


        lbLastname = new Label("Last Name");
        lbFirstname = new Label("First Name");
        lbUsername = new Label("Username");
        lbPassword = new Label("Password");
        lbrole = new Label("Role");


        txtLastname = new TextField();
        txtFirstname = new TextField();
        txtUsername = new TextField();
        txtPassword = new TextField();
        comrole = new ComboBox<>();
        comrole.getItems().addAll("0", "1");
        new AutoCompleteComboBoxListener<>(comrole);
        comrole.setEditable(true);


        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.TOP_CENTER);
        mainPane.setPadding(new Insets(5,5,5,5));
        mainPane.setHgap(5);
        mainPane.setVgap(5);

        mainPane.add(lbLastname, 1,0);
        GridPane.setHalignment(lbLastname, HPos.RIGHT);
        GridPane.setValignment(lbLastname, VPos.CENTER);
        mainPane.add(txtLastname, 2,0);
        GridPane.setHalignment(txtLastname, HPos.LEFT);
        GridPane.setValignment(txtLastname, VPos.CENTER);

        mainPane.add(lbFirstname, 1,1);
        GridPane.setHalignment(lbFirstname, HPos.RIGHT);
        GridPane.setValignment(lbFirstname, VPos.CENTER);
        mainPane.add(txtFirstname, 2,1);
        GridPane.setHalignment(txtFirstname, HPos.LEFT);
        GridPane.setValignment(txtFirstname, VPos.CENTER);

        mainPane.add(lbUsername, 1,2);
        GridPane.setHalignment(lbUsername, HPos.RIGHT);
        GridPane.setValignment(lbUsername, VPos.CENTER);
        mainPane.add(txtUsername, 2,2);
        GridPane.setHalignment(txtUsername, HPos.LEFT);
        GridPane.setValignment(txtUsername, VPos.CENTER);

        mainPane.add(lbPassword, 1,3);
        GridPane.setHalignment(lbPassword, HPos.RIGHT);
        GridPane.setValignment(lbPassword, VPos.CENTER);
        mainPane.add(txtPassword, 2,3);
        GridPane.setHalignment(txtPassword, HPos.LEFT);
        GridPane.setValignment(txtPassword, VPos.CENTER);

        mainPane.add(lbrole, 1,4);
        GridPane.setHalignment(lbrole, HPos.RIGHT);
        GridPane.setValignment(lbrole, VPos.CENTER);
        mainPane.add(comrole, 2,4);
        GridPane.setHalignment(comrole, HPos.LEFT);
        GridPane.setValignment(comrole, VPos.CENTER);


        myUsers = new ListView<User>();
//        myUsers.getItems().addAll(rafail, nikos);
        loadFile();
        myUsers.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    loadForm(newValue);
                    System.out.println(newValue);
                });
        myUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        mainPane.add(myUsers, 0,0);
        GridPane.setHalignment(myUsers, HPos.LEFT);
        GridPane.setRowSpan(myUsers, mainPane.getRowCount()+5);

        btnNew = new Button();
        btnNew.setGraphic(new ImageView(new Image("C:\\Users\\joker\\IdeaProjects\\ExerUsersFiles\\src\\add35.png")));
        btnNew.setOnAction(e-> {
            enumState = enumUserState.ADD;
            changeState(enumState, null);
        });

        btnEdit = new Button();
        btnEdit.setGraphic(new ImageView(new Image("C:\\Users\\joker\\IdeaProjects\\ExerUsersFiles\\src\\edit35.png")));
        btnEdit.setOnAction(e-> {
            enumState = enumUserState.EDIT;
            changeState(enumState, myUsers.getSelectionModel().getSelectedItem());
        });

        btnDelete = new Button();
        btnDelete.setGraphic(new ImageView(new Image("C:\\Users\\joker\\IdeaProjects\\ExerUsersFiles\\src\\del35.png")));
        btnDelete.setOnAction(e-> {
            MessageOKCancel mb = new MessageOKCancel("Are you sure??", "Warning");
            boolean response = mb.getResponse();
            if (response) {
                myUsers.getItems().remove(myUsers.getSelectionModel().getSelectedItem());
                if (myUsers.getItems().size() > 0) {
                    myUsers.getSelectionModel().select(0);
                } else {
                    enumState = enumUserState.ADD;
                    changeState(enumState, null);
                }
            }
        });


        hBox = new HBox();
        hBox.setSpacing(3);
        hBox.getChildren().addAll(btnNew, btnEdit, btnDelete);

        mainPane.add(hBox, 0, mainPane.getRowCount()+1);

        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPrefWidth(200);
        col0.setHalignment(HPos.LEFT);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);

        Region rEmpty = new Region();
        mainPane.add(rEmpty, 3, 0);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(Priority.SOMETIMES);

        mainPane.getColumnConstraints().addAll(col0, col1, col2, col3);

        btnSave = new Button("Save");
        btnSave.setOnAction(e-> {
            if (enumState==enumUserState.ADD) {
                if (myUsers.getItems().size()<10) {
                    User c = new User();
                    saveForm(c);
                    myUsers.getItems().add(c);
                    enumState = enumUserState.VIEW;
                    myUsers.getSelectionModel().select(c);
                    changeState(enumState, c);
                } else {
                    MessageOKCancel mb = new MessageOKCancel("You can't exceed 10 users!", "Error");
                }

            } else if (enumState == enumUserState.EDIT) {
                User c = myUsers.getSelectionModel().getSelectedItem();
                saveForm(c);
                enumState = enumUserState.VIEW;
                changeState(enumState, c);
            }
        });



        btnCancel2 = new Button("Cancel");
        btnCancel2.setOnAction(e-> {
            enumState = enumUserState.VIEW;
            User c = myUsers.getItems().get(0);
            changeState(enumState, c);
        });


        paneSaveCancel = new HBox(btnSave, btnCancel2);
        paneSaveCancel.setSpacing(5);

        mainPane.add(paneSaveCancel, 2, 6);

        Scene scene = new Scene(mainPane);

        stage = new Stage();
        stage.setTitle("User Editing");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);

        myUsers.getSelectionModel().select(0);
        enumState = enumUserState.VIEW;
        changeState(enumState, myUsers.getSelectionModel().getSelectedItem());
        stage.show();
        stage.setOnCloseRequest(e-> {
            saveFile();
        });
    }

    public void saveForm (User user) {
        user.setLastname(txtLastname.getText());
        user.setFirstname(txtFirstname.getText());
        user.setUsername(txtUsername.getText());
        user.setPassword(txtPassword.getText());
        user.setRole(String.valueOf(comrole.getValue()));
    }

    public void loadForm (User user) {
        txtLastname.setText(user.getLastname());
        txtFirstname.setText(user.getFirstname());
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        comrole.setValue(user.getRole());
    }

    public void changeState (enumUserState enumState, User user) {
        switch (enumState) {
            case ADD:
                txtLastname.setEditable(true);
                txtFirstname.setEditable(true);
                txtUsername.setEditable(true);
                txtPassword.setEditable(true);
                comrole.setEditable(true);

                txtLastname.setText("");
                txtFirstname.setText("");
                txtUsername.setText("");
                txtPassword.setText("");

                myUsers.setEditable(false);
                paneSaveCancel.setVisible(true);
                hBox.setVisible(false);



                break;

            case EDIT:
                txtLastname.setEditable(true);
                txtFirstname.setEditable(true);
                txtUsername.setEditable(true);
                txtPassword.setEditable(true);
                comrole.setEditable(true);

                loadForm(user);

                myUsers.setEditable(false);
                paneSaveCancel.setVisible(true);
                hBox.setVisible(false);
                break;

            case VIEW:
                txtLastname.setEditable(false);
                txtFirstname.setEditable(false);
                txtUsername.setEditable(false);
                txtPassword.setEditable(false);
                comrole.setEditable(false);

                loadForm(user);

                myUsers.setEditable(true);
                paneSaveCancel.setVisible(false);
                hBox.setVisible(true);
                break;
        }
    }
    public void saveFile () {
        try (ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream
                            (new FileOutputStream(f)))) {
            for (User user: myUsers.getItems()) {
                os.writeObject(user);
            }
        } catch (IOException b) {
            MessageOKCancel mb = new MessageOKCancel(b.getMessage(), "Error");
        }
    }
    public void loadFile () {
        try (ObjectInputStream os = new ObjectInputStream(new BufferedInputStream
                                    (new FileInputStream(f)))){
            while (true) {
                myUsers.getItems().add((User) os.readObject());
            }

        } catch (IOException | ClassNotFoundException b) {
//            MessageOKCancel mb = new MessageOKCancel(b.getMessage(), "Error");
        }
    }
}
