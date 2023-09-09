package s21.helpers;

@OrmForm(fileName = "user_form.html", action = "/users", method = "post")
public class UserForm {
    @OrmInput(type = "text", name = "Armen", placeholder = "Enter First Name")
    private String firstName;
    @OrmInput(type = "text", name = "Arzumanyan", placeholder = "Enter Last Name")
    private String lastName;
    @OrmInput(type = "password", name = "15061999", placeholder = "Enter Password")
    private String password;
}