USE [bddQcm]
GO
/****** Object:  Table [dbo].[EPREUVE]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EPREUVE](
	[idEpreuve] [int] IDENTITY(1,1) NOT NULL,
	[dateDebutValidite] [datetime] NOT NULL,
	[dateFinValidite] [datetime] NOT NULL,
	[tempsEcoule] [time](7) NULL,
	[etat] [nchar](2) NOT NULL,
	[noteObtenue] [float] NULL,
	[niveauObtenu] [nchar](3) NULL,
	[idTest] [int] NULL,
	[idUtilisateur] [int] NULL,
 CONSTRAINT [PK_EPREUVE] PRIMARY KEY CLUSTERED 
(
	[idEpreuve] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PROFIL]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PROFIL](
	[idProfil] [int] IDENTITY(1,1) NOT NULL,
	[libelleProfil] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_PROFIL] PRIMARY KEY CLUSTERED 
(
	[idProfil] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PROMOTION]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PROMOTION](
	[idPromotion] [int] IDENTITY(1,1) NOT NULL,
	[libellePromotion] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_PROMOTION] PRIMARY KEY CLUSTERED 
(
	[idPromotion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PROPOSITION]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PROPOSITION](
	[idProposition] [int] IDENTITY(1,1) NOT NULL,
	[enonceProposition] [nvarchar](max) NOT NULL,
	[estBonne] [bit] NOT NULL,
	[idQuestion] [int] NULL,
 CONSTRAINT [PK_PROPOSITION] PRIMARY KEY CLUSTERED 
(
	[idProposition] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[QUESTION]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QUESTION](
	[idQuestion] [int] IDENTITY(1,1) NOT NULL,
	[enonce] [nvarchar](max) NOT NULL,
	[media] [nvarchar](max) NULL,
	[points] [int] NOT NULL,
	[idTheme] [int] NULL,
 CONSTRAINT [PK_QUESTION] PRIMARY KEY CLUSTERED 
(
	[idQuestion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[QUESTION_TIRAGE]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QUESTION_TIRAGE](
	[estMarquee] [bit] NOT NULL,
	[numOrdre] [int] NOT NULL,
	[IdEpreuve] [int] NULL,
	[IdQuestion] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[REPONSE_TIRAGE]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[REPONSE_TIRAGE](
	[idProposition] [int] NOT NULL,
	[idQuestion] [int] NOT NULL,
	[idEpreuve] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SECTION_TEST]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SECTION_TEST](
	[nbQuestionsATirer] [nchar](10) NOT NULL,
	[idTest] [int] NOT NULL,
	[idTheme] [int] NOT NULL,
 CONSTRAINT [PK_SECTION_TEST] PRIMARY KEY CLUSTERED 
(
	[idTest] ASC,
	[idTheme] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TEST]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TEST](
	[idTest] [int] IDENTITY(1,1) NOT NULL,
	[libelleTest] [nvarchar](max) NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[duree] [time](7) NOT NULL,
	[seuilHaut] [int] NOT NULL,
	[seuilBas] [int] NOT NULL,
 CONSTRAINT [PK_TEST] PRIMARY KEY CLUSTERED 
(
	[idTest] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[THEME]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[THEME](
	[idTheme] [int] IDENTITY(1,1) NOT NULL,
	[libelleTheme] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_THEME] PRIMARY KEY CLUSTERED 
(
	[idTheme] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UTILISATEUR]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UTILISATEUR](
	[idUtilisateur] [int] IDENTITY(1,1) NOT NULL,
	[nomUtilisateur] [nvarchar](max) NOT NULL,
	[prenomUtilisateur] [nvarchar](max) NOT NULL,
	[email] [nvarchar](max) NOT NULL,
	[password] [nvarchar](max) NOT NULL,
	[idProfil] [int] NOT NULL,
	[idPromotion] [int] NULL,
 CONSTRAINT [PK_UTILISATEUR] PRIMARY KEY CLUSTERED 
(
	[idUtilisateur] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
ALTER TABLE [dbo].[EPREUVE]  WITH CHECK ADD  CONSTRAINT [FK_EPREUVE_TEST] FOREIGN KEY([idTest])
REFERENCES [dbo].[TEST] ([idTest])
GO
ALTER TABLE [dbo].[EPREUVE] CHECK CONSTRAINT [FK_EPREUVE_TEST]
GO
ALTER TABLE [dbo].[PROPOSITION]  WITH CHECK ADD  CONSTRAINT [FK_PROPOSITION_QUESTION] FOREIGN KEY([idQuestion])
REFERENCES [dbo].[QUESTION] ([idQuestion])
GO
ALTER TABLE [dbo].[PROPOSITION] CHECK CONSTRAINT [FK_PROPOSITION_QUESTION]
GO
ALTER TABLE [dbo].[QUESTION]  WITH CHECK ADD  CONSTRAINT [FK_QUESTION_THEME] FOREIGN KEY([idTheme])
REFERENCES [dbo].[THEME] ([idTheme])
GO
ALTER TABLE [dbo].[QUESTION] CHECK CONSTRAINT [FK_QUESTION_THEME]
GO
ALTER TABLE [dbo].[QUESTION_TIRAGE]  WITH CHECK ADD  CONSTRAINT [FK_QUESTION_TIRAGE_EPREUVE] FOREIGN KEY([IdEpreuve])
REFERENCES [dbo].[EPREUVE] ([idEpreuve])
GO
ALTER TABLE [dbo].[QUESTION_TIRAGE] CHECK CONSTRAINT [FK_QUESTION_TIRAGE_EPREUVE]
GO
ALTER TABLE [dbo].[QUESTION_TIRAGE]  WITH CHECK ADD  CONSTRAINT [FK_QUESTION_TIRAGE_QUESTION] FOREIGN KEY([IdQuestion])
REFERENCES [dbo].[QUESTION] ([idQuestion])
GO
ALTER TABLE [dbo].[QUESTION_TIRAGE] CHECK CONSTRAINT [FK_QUESTION_TIRAGE_QUESTION]
GO
ALTER TABLE [dbo].[SECTION_TEST]  WITH CHECK ADD  CONSTRAINT [FK_SECTION_TEST_TEST] FOREIGN KEY([idTest])
REFERENCES [dbo].[TEST] ([idTest])
GO
ALTER TABLE [dbo].[SECTION_TEST] CHECK CONSTRAINT [FK_SECTION_TEST_TEST]
GO
ALTER TABLE [dbo].[SECTION_TEST]  WITH CHECK ADD  CONSTRAINT [FK_SECTION_TEST_THEME] FOREIGN KEY([idTheme])
REFERENCES [dbo].[THEME] ([idTheme])
GO
ALTER TABLE [dbo].[SECTION_TEST] CHECK CONSTRAINT [FK_SECTION_TEST_THEME]
GO
ALTER TABLE [dbo].[UTILISATEUR]  WITH CHECK ADD  CONSTRAINT [FK_UTILISATEUR_PROFIL] FOREIGN KEY([idProfil])
REFERENCES [dbo].[PROFIL] ([idProfil])
GO
ALTER TABLE [dbo].[UTILISATEUR] CHECK CONSTRAINT [FK_UTILISATEUR_PROFIL]
GO
ALTER TABLE [dbo].[UTILISATEUR]  WITH CHECK ADD  CONSTRAINT [FK_UTILISATEUR_PROMOTION] FOREIGN KEY([idPromotion])
REFERENCES [dbo].[PROMOTION] ([idPromotion])
GO
ALTER TABLE [dbo].[UTILISATEUR] CHECK CONSTRAINT [FK_UTILISATEUR_PROMOTION]
GO
/****** Object:  StoredProcedure [dbo].[GetResult]    Script Date: 17/10/2018 16:34:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetResult] @Epreuve int
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
