package lab5;

public class Manager {

    void dropDatabaseStructure(){
        GradeDB.dropGradeTable();
        SubjectDB.dropSubjectTable();
        StudentDB.dropStudentTable();
    }

    void createDatabaseStructure(){
        StudentDB.createStudentTable();
        SubjectDB.createSubjectTable();
        GradeDB.createGradeTable();
    }


}
