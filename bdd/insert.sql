use bddQcm;

insert into PROFIL(libelleProfil) values ('administrateur');
insert into PROFIL(libelleProfil) values ('formateur');
insert into PROFIL(libelleProfil) values ('eleve');

insert into PROMOTION(libellePromotion) values ('CDI2017');
insert into PROMOTION(libellePromotion) values ('CDI2018');

insert into UTILISATEUR(nomUtilisateur, prenomUtilisateur, email, password, idProfil, idPromotion) VALUES ('pop','popPrenom','pop@pop0fr', 'pop', 3, 1);

insert into TEST(libelleTest, description, duree, seuilHaut, seuilBas ) values ('Java EE', 'Ceci est un test Java de qualité', '01:00:00', 14, 8);
insert into TEST(libelleTest, description, duree, seuilHaut, seuilBas ) values ('SQL', 'Ceci est un test sql', '02:00:00', 14, 8);

insert into THEME(libelleTheme) values ('Les variables java');
insert into THEME(libelleTheme) values ('Les servelts java');
insert into THEME(libelleTheme) values ('Les jsp java');

insert into THEME(libelleTheme) values ('MYSQL');
insert into THEME(libelleTheme) values ('Oracle');

insert into SECTION_TEST(nbQuestionsATirer, idTest, idTheme) values (10, 1, 1);
insert into SECTION_TEST(nbQuestionsATirer, idTest, idTheme) values (10, 1, 2);
insert into SECTION_TEST(nbQuestionsATirer, idTest, idTheme) values (5, 1, 3);

insert into SECTION_TEST(nbQuestionsATirer, idTest, idTheme) values (15, 2, 3);
insert into SECTION_TEST(nbQuestionsATirer, idTest, idTheme) values (15, 2, 4);

insert into EPREUVE(dateDebutValidite, dateFinValidite, etat, idTest, idUtilisateur) values ('2018-10-08 10:00:00', '2020-10-08 10:00:00', 'EA', 1, 1);
insert into EPREUVE(dateDebutValidite, dateFinValidite, etat, idTest, idUtilisateur) values ('2018-11-04 08:00:00', '2020-11-08 10:00:00', 'EA', 2, 1);

insert into QUESTION(enonce, points, idTheme) values ('Un servlet est : ', 1, 2);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Un composant web', 1, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Un composant métier', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Une interface java', 0, 1);


