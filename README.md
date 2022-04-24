# AdvancedProgrammingProject (2021)

- A Client-Server application for a cable company
- Written in Java
- Multithreaded
- Hibernate Framework
- Live Chat

# Features

- Customers can make a complaint, view compalint history, bill payment details, and initiate live chat with Reps & Technicians
- Representatives can view complaint, respond to complaint, assign complaint to a technician, and view & modify tables in the database.
- Technicians can view complaints assigned to them, respond to complaints, specify if they are available for live chat and choose a visit date.

# Screenshots
* server 

![server](https://user-images.githubusercontent.com/43584021/164993500-47abae14-5e3c-45ae-9873-c5c2c3d636d7.png)

* Login UIs

![client login ui](https://user-images.githubusercontent.com/43584021/164993568-1835eee8-5905-4cb5-b1c4-1c10de3aedae.png)
![rep login ui](https://user-images.githubusercontent.com/43584021/164993583-528eac28-e4ba-4ce6-81a8-c0a50f4a1114.png)
![tech login ui](https://user-images.githubusercontent.com/43584021/164993586-06486532-944d-45f9-a454-48c33e576d42.png)


* Dashboard UIs

![client dashboard](https://user-images.githubusercontent.com/43584021/164993632-201cf12c-4b45-4dd0-9e9d-bd7c8dc8fb64.png)
![rep dashboard](https://user-images.githubusercontent.com/43584021/164993635-680ad009-bce4-4467-ae74-9497ecbac909.png)
![tech dashboard](https://user-images.githubusercontent.com/43584021/164993639-744e7669-7034-45ee-9053-cf75a0ddcd37.png)

# Setup

XAMPP Server is required
* From xampp control panel start the MySQL Module
* Go to MySQL admin and import the "final_approject.sql" file found in the root of "APProjectServer" folder.

* Run ServerController.java to launch Server. [found in the server prject]
* Run MainController.java to launch the Client side applications. [found in the client project]






