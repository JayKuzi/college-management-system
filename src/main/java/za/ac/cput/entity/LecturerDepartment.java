package za.ac.cput.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * LecturerDepartment.java;
 * This is a bridging entity between Lecturer and Department domain classes
 * Author: Anele Aneal Tose - 216079292
 * Date: 08 April 2022
 */

//TODO: Remove Entity mapping for bridging entity.
//@Entity
public class LecturerDepartment {
    //@Id
    //@NotNull
    private long lecturerId;
    //@Id
    //@NotNull
    private int departmentId;

    protected LecturerDepartment(){}
    private LecturerDepartment(Builder builder){
        this.lecturerId = builder.lecturerId;
        this.departmentId = builder.departmentId;

    }

    public long getLectureId() {
        return lecturerId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    @Override
    public String toString() {
        return "LecturerDepartment{" +
                "lectureId=" + lecturerId +
                ", departmentId=" + departmentId +
                '}';
    }
    public static class Builder{
        private long lecturerId;
        private int departmentId;

        public Builder setLecturerId(long lecturerId){
            this.lecturerId = lecturerId;
            return this;

        }
        public Builder setDepartmentId(int departmentId){
            this.departmentId = departmentId;
            return this;
        }
        public Builder copy(LecturerDepartment lecturerDepartment){
            this.lecturerId = lecturerDepartment.lecturerId;
            this.departmentId = lecturerDepartment.departmentId;
            return this;
        }
        public LecturerDepartment build(){
            return new LecturerDepartment(this);

        }

    }

}