package sample;

public class StudentRecord {
    // >> Class Parameters <<
    private String studentID;
    private double midterm, assignments, finalExam, finalMark;
    private char letterGrade;

    public StudentRecord(String studentID, double assignments, double midterm, double finalExam){
        this.studentID=studentID;
        this.assignments=assignments;
        this.midterm=midterm;
        this.finalExam=finalExam;
        this.finalMark=assignments * (0.2) + midterm * (0.3) + finalExam * (0.5);

        if (finalMark >= 80){
            this.letterGrade='A';
        }
        else if (finalMark >= 70){
            this.letterGrade='B';
        }
        else if (finalMark >= 60){
            this.letterGrade='C';
        }
        else if (finalMark >= 50){
            this.letterGrade='D';
        }
        else{
            this.letterGrade='F';
        }
    }

    public String getStudentID(){
        return this.studentID;
    }

    public double getMidterm(){
        return this.midterm;
    }

    public double getAssignments(){
        return this.assignments;
    }

    public double getFinalExam(){
        return this.finalExam;
    }

    public double getFinalMark(){
        return this.finalMark;
    }

    public char getLetterGrade(){
        return this.letterGrade;
    }
}
