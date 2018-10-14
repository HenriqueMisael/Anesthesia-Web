package br.uem.iss.anesthesia.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private String address;
    private String phoneNumber;
    private String cellphoneNumber;
    private String email;
    private String cpf;
    private String rg;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<BackgroundModel> backgrounds;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<MedicineModel> medicines;
    private boolean active = true;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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

    public Set<BackgroundModel> getBackgrounds() {
        return backgrounds;
    }

    public void setBackgrounds(Set<BackgroundModel> backgrounds) {
        this.backgrounds = backgrounds;
    }

    public Set<MedicineModel> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<MedicineModel> medicines) {
        this.medicines = medicines;
    }

    public boolean isActive() {
        return active;
    }

    public void inactivate() {
        active = false;
    }

    public void activate() {
        active = true;
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
        Set<BackgroundModel> clonedBackgrounds = new HashSet<>();
        copy(clonedBackgrounds, getBackgrounds());
        Set<MedicineModel> clonedMedicines = new HashSet<>();
        copy(clonedMedicines, getMedicines());
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

    private void copy(Set target, Set source) {
        for (Object o : source) {
            target.add(o);
        }
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }
}
