drop database if exists Bookster;
create database Bookster;
use Bookster;

CREATE TABLE Lettore(
                        username varchar(20) primary key,
                        psw varchar(100) not null,
                        nome varchar(50) not null,
                        cognome varchar(50) not null,
                        email varchar(100) not null,
                        ddn date not null,
                        telefono varchar(11),
                        genere varchar(20),
                        punteggio int not null
);

CREATE TABLE Classifica(
                           id int(10) primary key,
                           partecipanti int(255) not null,
                           periodo date not null
);

CREATE TABLE Libro(
                      isbn varchar(13) primary key,
                      titolo varchar(100) not null,
                      autore varchar(30) not null,
                      pathcopertina varchar(255) not null default("none.jpg"),
                      pagine int  not null
);

CREATE TABLE Libreria(
                         nome varchar(13) not null,
                         username varchar(20) not null,
                         libri varchar(13) not null,
                         foreign key(nome) references Libro(isbn),
                         foreign key(username) references Lettore(username)
);

CREATE TABLE Recensione(
                           id int(10) primary key,
                           stelle decimal(2,1) not null,
                           testo varchar(255) not null
);

INSERT INTO Lettore VALUES
                        ("Goor1979",SHA1("tie1IJiengei"),"Demetrio","De Luca","demetriodeluca@teleworm.us",'2001-02-24',"3456003403",null,0),
                        ("Howl1944",SHA1("pei7Hoh0f"),"Ninfa", "Lo Duca","ninfaloduca@armyspy.com",'1987-10-05',"3456003403",null,50);