# DemoStudentRegistry

This is a basic shell project, modelling a student registry.

I've made a private repo for this for now https://github.com/MS35/DemoStudentRegistry.git

To set up, the local machine will need postgresql installed and a default "postgres" user  and a default "testDB" to be created.
Under src/resources in the application.properties I've set the password to "postgres" to start off.
The local machine will also need maven command line tools installed.

### The domain objects I've chosen are  
 - Student (unique constraints being name and surname)  
 - Score (studentId being a foreign key)  
 - User  

### Managed to Complete:
 - Basic create read and delete functionality should be working for Students, Scores and Users.  
 - Auto generated student IDs using first and last names aUpdate has not yet been achieved.  
 - User/Admin separation for AddStudent-AddScore tabs  
 - Some pattern matching for emails  
 - Strong password validation  
 - Can access pages using PostMan once deployed  

### Also not completed are:
 - Error messaging/logging for the user (5)  
 - Current score for the student.(3)  
 - Vue.js(5)  
 - Student Profile view with current score and update functionality(2)  
 - Dockerised Environment(8)  
 - Unit Tests(3)  
 - Complete Documentation(2)  
 - +27 etc area codes for cell numbers(3)  
 - Final UI styling (Was supposed to switch frameworks anyway)(3)  
 - Student Searching(2)  


### I've used:
 - Java 8  
 - Spring/Spring-Boot  
 - Primefaces and JSF  