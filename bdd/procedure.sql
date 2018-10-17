CREATE PROCEDURE dbo.GetResult @Epreuve int
AS
SELECT t2.idQuestion, ISNULL(CAST(bonneReponseSelectionne AS float) / CAST(nbBonneReponse AS float), 0) as pts
FROM 
(
	SELECT qt.idQuestion, count(estBonne) as bonneReponseSelectionne
	FROM QUESTION_TIRAGE qt
	JOIN REPONSE_TIRAGE rt ON qt.idQuestion = rt.idQuestion
	JOIN PROPOSITION p ON rt.idProposition = p.idProposition
	WHERE rt.IdEpreuve = @Epreuve
	AND estBonne = 1
	GROUP BY qt.idQuestion) as t1
right join (
	SELECT qt.idQuestion, count(estBonne) as nbBonneReponse
	FROM QUESTION_TIRAGE qt
	JOIN PROPOSITION p ON qt.idQuestion = p.idQuestion
	WHERE qt.IdEpreuve = @Epreuve
	AND estBonne = 1
	GROUP BY qt.idQuestion
) as t2 ON t1.idQuestion = t2.idQuestion;
GO