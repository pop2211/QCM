package fr.eni.tp.qcm.dal.dao.impl;

public class SectionTestDAOImpl {


    private static final String SELECT_SECTION_TEST_QUERY = "SELECT idTest, libelle, description, duree, seuil_haut, seuil_bas FROM TEST WHERE idTest = ?";
    private static final String INSERT_SECTION_TEST_QUERY = "INSERT INTO TEST(libelle, description, duree, seuil_haut, seuil_bas) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_SECTION_TEST_QUERY = "DELETE FROM TEST WHERE idTest = ?";
    private static final String UPDATE_SECTION_TEST_QUERY = "UPDATE TEST SET libelle = ?, descripion = ?, duree = ?, seuil_haut = ?, seuil_bas = ? WHERE idTest = ?";
    
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
