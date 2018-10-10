package fr.eni.tp.qcm.dal.dao.impl;

public class SectionTestDAOImpl {


    private static final String SELECT_SECTION_TEST_BY_TEST_QUERY = "SELECT idTest, idTheme, nbQuestionsATrier FROM SECTION_TEST WHERE idTest = ?";
    private static final String INSERT_SECTION_TEST_QUERY = "INSERT INTO SECTION_TEST(idTest, idTheme, nbQuestionsATrier) VALUES (?, ?, ?)";
    private static final String DELETE_SECTION_TEST_QUERY = "DELETE FROM SECTION_TEST WHERE idTest = ?";
    private static final String UPDATE_SECTION_TEST_QUERY = "UPDATE SECTION_TEST SET idTheme = ?, nbQuestionsATrier = ? WHERE idTest = ?";
    
    private static SectionTestDAOImpl instance;
    
    private SectionTestDAOImpl() {
        
    }
    
    public static SectionTestDAOImpl getInstance() {
        if(instance == null) {
            instance = new SectionTestDAOImpl();
        }
        return instance;
    }
    
}
