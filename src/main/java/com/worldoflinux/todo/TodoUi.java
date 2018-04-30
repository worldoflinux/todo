package com.worldoflinux.todo;



import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.annotations.Theme;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.event.ShortcutAction;


@SpringUI
@Theme ("valo")




public class TodoUi extends UI {


    private VerticalLayout layout ;

    @Autowired
      TodoList  todoList;

             @Override
             protected void init (VaadinRequest vaadinRequest) {
                 setupLayout();
                 addHeader();
                 addForm();
                 addTodoList();
                 addActionButtons();

             }

             private void setupLayout() {
                 layout = new VerticalLayout();
                 layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
                 setContent(layout);
             }


             private void addHeader(){
                Label header = new Label("Todo Application");
                header.addStyleName(ValoTheme.LABEL_H1);
                layout.addComponent(header);
             }

             private void addForm() {


                 HorizontalLayout FormLayout = new HorizontalLayout();
                 FormLayout.setWidth("80%");
                 TextField taskField = new TextField();
                 taskField.focus();
                 Button addButton = new Button("");

                 FormLayout.addComponentsAndExpand(taskField);
                 FormLayout.addComponent(addButton);
                 layout.addComponent(FormLayout);

                 addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
                 addButton.setIcon(VaadinIcons.PLUS);

                 addButton.addClickListener(click -> {
                     todoList.addTodo(new Todo(taskField.getValue()));
                     taskField.setValue("");
                     taskField.focus();

                 });
                 addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);



             }
              private void addTodoList() {
                  layout.addComponent(todoList);

              }

              private void addActionButtons() {
                  Button deleteButton = new Button("Delete completed items");

                  deleteButton.addClickListener(click->todoList.deleteCompleted());

                  layout.addComponent(deleteButton);

              }
    }
