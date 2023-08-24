package cz.engeto.projekt2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class UserResponse {
    private String id;
    private String name;
    private String surname;
    private String personId;
    private String personUuid ;
    @JsonIgnore
    private boolean detail;

    public UserResponse() {
    }

    public UserResponse(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public UserResponse(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public UserResponse(String name, String surname, String personId, String personUuid) {
        this.name = name;
        this.surname = surname;
        this.personId = personId;
        this.personUuid = personUuid;
    }

    public UserResponse(String id, String name, String surname, String personId, String personUuid) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personId = personId;
        this.personUuid = personUuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonUuid() {
        return personUuid;
    }

    public void setPersonUuid(String personUuid) {
        this.personUuid = personUuid;
    }

    public boolean getDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }
}
