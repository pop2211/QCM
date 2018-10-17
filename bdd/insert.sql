use bddQcm;

insert into PROFIL(libelleProfil) values ('administrateur');
insert into PROFIL(libelleProfil) values ('responsable');
insert into PROFIL(libelleProfil) values ('formateur');
insert into PROFIL(libelleProfil) values ('eleve');

insert into PROMOTION(libellePromotion) values ('CDI2017');
insert into PROMOTION(libellePromotion) values ('CDI2018');

insert into UTILISATEUR(nomUtilisateur, prenomUtilisateur, email, password, idProfil, idPromotion) VALUES ('pop','popPrenom','pop@pop.fr', 'pop', 3, null);
insert into UTILISATEUR(nomUtilisateur, prenomUtilisateur, email, password, idProfil, idPromotion) VALUES ('sar','Sandra','sar@sar.fr', 'sar', 4, 1);
insert into UTILISATEUR(nomUtilisateur, prenomUtilisateur, email, password, idProfil, idPromotion) VALUES ('jea','Jean','jea@jea.fr', 'jea', 4, 1);

insert into TEST(libelleTest, description, duree, seuilHaut, seuilBas ) values ('Java EE', 'Ceci est un test Java de qualité', '01:00:00', 70, 40);
insert into TEST(libelleTest, description, duree, seuilHaut, seuilBas ) values ('SQL', 'Ceci est un test sql', '02:00:00', 70, 40);
insert into TEST(libelleTest, description, duree, seuilHaut, seuilBas ) values ('PHP', 'Ceci est un test php', '02:00:00', 70, 40);

insert into THEME(libelleTheme) values ('Les variables java');
insert into THEME(libelleTheme) values ('Les servlets java');
insert into THEME(libelleTheme) values ('Les jsp java');
insert into THEME(libelleTheme) values ('MYSQL');
insert into THEME(libelleTheme) values ('Oracle');
insert into THEME(libelleTheme) values ('PHP');


insert into SECTION_TEST(nbQuestionsATirer, idTest, idTheme) values (10, 1, 1);
insert into SECTION_TEST(nbQuestionsATirer, idTest, idTheme) values (10, 1, 2);
insert into SECTION_TEST(nbQuestionsATirer, idTest, idTheme) values (5, 1, 3);
insert into SECTION_TEST(nbQuestionsATirer, idTest, idTheme) values (15, 2, 3);
insert into SECTION_TEST(nbQuestionsATirer, idTest, idTheme) values (15, 2, 4);

insert into EPREUVE(dateDebutValidite, dateFinValidite, etat, idTest, idUtilisateur) values ('2018-10-08 10:00:00', '2020-10-08 10:00:00', 'EA', 1, 1);
insert into EPREUVE(dateDebutValidite, dateFinValidite, etat, idTest, idUtilisateur) values ('2018-11-04 08:00:00', '2020-11-08 10:00:00', 'EA', 2, 1);
insert into EPREUVE(dateDebutValidite, dateFinValidite, etat, idTest, idUtilisateur) values ('2018-09-04 10:30:00', '2020-09-04 20:30:00', 'EA', 3, 1);

insert into QUESTION(enonce, points, idTheme) values ('Un servlet est : ', 1, 2);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Un composant web', 1, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Un composant métier', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Une interface java', 0, 1);
insert into QUESTION(enonce, points, idTheme) values ('De quelle classe hérite une servlet? : ', 1, 2);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('ServletRequest', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('HttpServlet', 1, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Servlet', 0, 1);
insert into QUESTION(enonce, points, idTheme) values ('Quelle méthode permet une lecture de paramètre? : ', 1, 2);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('setParameter(String name)', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('getParameter(String name)', 1, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('parameter(String name)', 0, 1);
insert into QUESTION(enonce, points, idTheme) values ('Quelle méthode permet de renvoyer un message d''erreur? : ', 1, 2);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('error(...)', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('setError(...)', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('sendError(...)', 1, 1);

insert into QUESTION(enonce, points, idTheme) values ('Que signifie PHP? : ', 1, 3);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Hypertext Preprocessor', 1, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Personal Home Page', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Personal Home Page', 0, 1);
insert into QUESTION(enonce, points, idTheme) values ('Quels sont les délimiteurs du langage PHP  ? : ', 1, 3);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('<? ... ?>', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('<?php ... ?>', 1, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('<% %>', 0, 1);
insert into QUESTION(enonce, points, idTheme) values ('Laquelle de ces trois syntaxes est exacte si je souhaite créer une fonction nommée "bonjour" en PHP ? : ', 1, 3);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('fonction bonjour{} ;', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('fonction bonjour(){} ;', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('function bonjour(){}.', 1, 1);
insert into QUESTION(enonce, points, idTheme) values ('Pour afficher simplement un valeur en PHP, on préférera utiliser l''instruction... ? : ', 1, 3);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('echo ;', 1, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('print ;', 0, 1);
insert into PROPOSITION(enonceProposition, estBonne, idQuestion) values ('Les deux instructions sont équivalentes.', 0, 1);