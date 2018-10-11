use bddQcm;

insert into PROFIL(libelleProfil) values ('administrateur');
insert into PROFIL(libelleProfil) values ('formateur');
insert into PROFIL(libelleProfil) values ('eleve');

insert into PROMOTION(libellePromotion) values ('CDI2017');
insert into PROMOTION(libellePromotion) values ('CDI2018');

insert into UTILISATEUR(nomUtilisateur, prenomUtilisateur, email, password, idProfil, idPromotion) VALUES ('pop','popPrenom','pop@pop0fr', 'pop', 3, 1);