create table users(user_id VARCHAR(36) NOT NULL,
first_name VARCHAR(40) NOT NULL,last_name VARCHAR(40) NOT NULL,
email VARCHAR(100) NOT NULL, user_password VARCHAR(254) NOT NULL,
user_type CHAR(1) NOT NULL,
CONSTRAINT user_type_discriminator CHECK (user_type in ('O','P')),
PRIMARY KEY (user_id));


create table organizer(user_id VARCHAR(36) NOT NULL PRIMARY KEY REFERENCES users( user_id) ON UPDATE CASCADE,
company_name VARCHAR(100) NOT NULL, biograpy TEXT NOT NULL,
linkedin_url VARCHAR(70), tweeter_url VARCHAR(70),
facebook_url  VARCHAR(70),	instagram_url VARCHAR(70));

create table participant(user_id VARCHAR(36) NOT NULL PRIMARY KEY REFERENCES users(user_id) ON UPDATE CASCADE,
participant_number INT NOT NULL);

create table event(event_id VARCHAR(36) NOT NULL,title VARCHAR(40) NOT NULL,
start_day DATE NOT NULL,
end_day DATE NOT NULL,
location VARCHAR(100) NOT NULL,
event_status BOOLEAN NOT NULL,
event_capacity INT NOT NULL,
user_id VARCHAR(36) NOT NULL,
PRIMARY KEY (event_id),
FOREIGN KEY (user_id) REFERENCES organizer(user_id) ON UPDATE CASCADE
 );

create table track(track_id VARCHAR(36) NOT NULL,
location VARCHAR(36) NOT NULL,
event_id VARCHAR(36) NOT NULL,
PRIMARY KEY (track_id),
FOREIGN KEY (event_id) REFERENCES event(event_id) ON UPDATE CASCADE);

create table session(session_id VARCHAR(36) NOT NULL,
tittle VARCHAR(40) NOT NULL,
session_description TEXT NOT NULL,
session_type VARCHAR(30) NOT NULL,
session_day DATE NOT NULL,
start_time TIME NOT NULL,
end_time TIME NOT NULL,
track_id VARCHAR(36) NOT NULL,
PRIMARY KEY (session_id),
FOREIGN KEY (track_id) REFERENCES track(track_id) ON UPDATE CASCADE);

create table speaker(speaker_id varchar(36) NOT NULL,
first_name VARCHAR(40) NOT NULL,
last_name VARCHAR(40) NOT NULL,
company_name VARCHAR(40) NOT NULL,
tittle VARCHAR(40) NOT NULL,
biograpy TEXT NOT NULL,
linkedin_url VARCHAR(70), tweeter_url VARCHAR(70),
facebook_url  VARCHAR(70),	instagram_url VARCHAR(70),
PRIMARY KEY (speaker_id));

create table session_speaker(speaker_id varchar(36) NOT NULL,
session_id varchar(36) NOT NULL,
FOREIGN KEY (session_id) REFERENCES session(session_id) ON UPDATE CASCADE,
FOREIGN KEY(speaker_id) REFERENCES speaker(speaker_id) ON UPDATE CASCADE);

create table speaker_rate(user_id VARCHAR(36) NOT NULL,
speaker_id VARCHAR(36) NOT NULL,
FOREIGN KEY (user_id) REFERENCES participant(user_id) ON UPDATE CASCADE,
FOREIGN KEY (speaker_id) REFERENCES speaker(speaker_id) ON UPDATE CASCADE,
rate INT CHECK (rate>0 AND rate<=5));


create table participant_session(user_id VARCHAR(36) NOT NULL,
session_id VARCHAR(36) NOT NULL,
rate INT CHECK (rate>0 AND rate<=5),
subscribe BOOLEAN ,
FOREIGN KEY (user_id) REFERENCES  participant(user_id) ON UPDATE CASCADE,
FOREIGN KEY (session_id) REFERENCES session(session_id) ON UPDATE CASCADE);






