package br.uem.iss.anesthesia.model.entity;

import br.uem.iss.anesthesia.model.entity.embedded.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Patients")
public class PatientModel extends DefaultModel {

    private String name;
    private String surname;
    private Gender gender;
    private LocalDate birthday;
    private String religion;
    private CivilState civilState;
    @ManyToOne
    private CityModel city;
    private String postalCode;
    @Embedded
    private Address address;
    private String phoneNumber;
    private String cellphoneNumber;
    private String email;
    private String cpf;
    private String rg;
    @ManyToMany
    private List<BackgroundModel> backgrounds;
    @ManyToMany
    private List<MedicineModel> medicines;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public CivilState getCivilState() {
        return civilState;
    }

    public void setCivilState(CivilState civilState) {
        this.civilState = civilState;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public List<BackgroundModel> getBackgrounds() {
        return backgrounds;
    }

    public void setBackgrounds(List<BackgroundModel> backgrounds) {
        this.backgrounds = backgrounds;
    }

    public List<MedicineModel> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineModel> medicines) {
        this.medicines = medicines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PatientModel))
            return false;
        PatientModel that = (PatientModel) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getSurname(), that.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname());
    }

    @Override
    protected PatientModel clone() throws CloneNotSupportedException {
        List<BackgroundModel> clonedBackgrounds = new ArrayList<>();
        Collections.copy(clonedBackgrounds, getBackgrounds());
        List<MedicineModel> clonedMedicines = new ArrayList<>();
        Collections.copy(clonedMedicines, getMedicines());

        PatientModel clone = (PatientModel) super.clone();
        clone.setName(getName());
        clone.setSurname(getSurname());
        clone.setGender(getGender());
        clone.setBirthday(getBirthday());
        clone.setReligion(getReligion());
        clone.setCivilState(getCivilState());
        clone.setCity(getCity());
        clone.setPostalCode(getPostalCode());
        clone.setAddress(getAddress());
        clone.setPhoneNumber(getPhoneNumber());
        clone.setCellphoneNumber(getCellphoneNumber());
        clone.setEmail(getEmail());
        clone.setCpf(getCpf());
        clone.setRg(getRg());
        clone.setBackgrounds(clonedBackgrounds);
        clone.setMedicines(clonedMedicines);

        return clone;
    }
}
