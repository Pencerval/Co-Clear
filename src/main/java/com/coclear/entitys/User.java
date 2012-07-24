/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coclear.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pencerval
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByBirthdate", query = "SELECT u FROM User u WHERE u.birthdate = :birthdate"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByIsAdmin", query = "SELECT u FROM User u WHERE u.isAdmin = :isAdmin"),
    @NamedQuery(name = "User.findByHearingLossDate", query = "SELECT u FROM User u WHERE u.hearingLossDate = :hearingLossDate"),
    @NamedQuery(name = "User.findByCauseDeafnes", query = "SELECT u FROM User u WHERE u.causeDeafnes = :causeDeafnes"),
    @NamedQuery(name = "User.findByDegreeDeafness", query = "SELECT u FROM User u WHERE u.degreeDeafness = :degreeDeafness"),
    @NamedQuery(name = "User.findByDurationHearingLoss", query = "SELECT u FROM User u WHERE u.durationHearingLoss = :durationHearingLoss"),
    @NamedQuery(name = "User.findByImplantationDate", query = "SELECT u FROM User u WHERE u.implantationDate = :implantationDate"),
    @NamedQuery(name = "User.findByCochlearImplantType", query = "SELECT u FROM User u WHERE u.cochlearImplantType = :cochlearImplantType"),
    @NamedQuery(name = "User.findByCochlearImplantModel", query = "SELECT u FROM User u WHERE u.cochlearImplantModel = :cochlearImplantModel"),
    @NamedQuery(name = "User.findBySignLanguage", query = "SELECT u FROM User u WHERE u.signLanguage = :signLanguage"),
    @NamedQuery(name = "User.findBySignLanguageYears", query = "SELECT u FROM User u WHERE u.signLanguageYears = :signLanguageYears"),
    @NamedQuery(name = "User.findBySpeechReading", query = "SELECT u FROM User u WHERE u.speechReading = :speechReading"),
    @NamedQuery(name = "User.findBySpeechReadingYears", query = "SELECT u FROM User u WHERE u.speechReadingYears = :speechReadingYears"),
    @NamedQuery(name = "User.findBySpeechTherapy", query = "SELECT u FROM User u WHERE u.speechTherapy = :speechTherapy"),
    @NamedQuery(name = "User.findBySpeechTherapyYears", query = "SELECT u FROM User u WHERE u.speechTherapyYears = :speechTherapyYears"),
    @NamedQuery(name = "User.findByEducationalOrientation", query = "SELECT u FROM User u WHERE u.educationalOrientation = :educationalOrientation"),
    @NamedQuery(name = "User.findByEducationalOrientationYears", query = "SELECT u FROM User u WHERE u.educationalOrientationYears = :educationalOrientationYears"),
    @NamedQuery(name = "User.findByBilingual", query = "SELECT u FROM User u WHERE u.bilingual = :bilingual"),
    @NamedQuery(name = "User.findByLanguages", query = "SELECT u FROM User u WHERE u.languages = :languages"),
    @NamedQuery(name = "User.findByHomeLanguages", query = "SELECT u FROM User u WHERE u.homeLanguages = :homeLanguages"),
    @NamedQuery(name = "User.findByLearningLenguages", query = "SELECT u FROM User u WHERE u.learningLenguages = :learningLenguages"),
    @NamedQuery(name = "User.findByLearningLenguagesList", query = "SELECT u FROM User u WHERE u.learningLenguagesList = :learningLenguagesList"),
    @NamedQuery(name = "User.findByPlaceYearsResidence", query = "SELECT u FROM User u WHERE u.placeYearsResidence = :placeYearsResidence"),
    @NamedQuery(name = "User.findByPlaceYearsResidenceMother", query = "SELECT u FROM User u WHERE u.placeYearsResidenceMother = :placeYearsResidenceMother"),
    @NamedQuery(name = "User.findByPlaceYearsResidenceFather", query = "SELECT u FROM User u WHERE u.placeYearsResidenceFather = :placeYearsResidenceFather"),
    @NamedQuery(name = "User.findByComments", query = "SELECT u FROM User u WHERE u.comments = :comments"),
    @NamedQuery(name = "User.findByAdminComments", query = "SELECT u FROM User u WHERE u.adminComments = :adminComments")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "gender")
    private Boolean gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Column(name = "hearing_loss_date")
    @Temporal(TemporalType.DATE)
    private Date hearingLossDate;
    @Size(max = 255)
    @Column(name = "cause_deafnes")
    private String causeDeafnes;
    @Size(max = 255)
    @Column(name = "degree_deafness")
    private String degreeDeafness;
    @Size(max = 255)
    @Column(name = "duration_hearing_loss")
    private String durationHearingLoss;
    @Column(name = "implantation_date")
    @Temporal(TemporalType.DATE)
    private Date implantationDate;
    @Size(max = 255)
    @Column(name = "cochlear_implant_type")
    private String cochlearImplantType;
    @Size(max = 255)
    @Column(name = "cochlear_implant_model")
    private String cochlearImplantModel;
    @Column(name = "sign_language")
    private Boolean signLanguage;
    @Column(name = "sign_language_years")
    private Integer signLanguageYears;
    @Column(name = "speech_reading")
    private Boolean speechReading;
    @Column(name = "speech_reading_years")
    private Integer speechReadingYears;
    @Column(name = "speech_therapy")
    private Boolean speechTherapy;
    @Column(name = "speech_therapy_years")
    private Integer speechTherapyYears;
    @Column(name = "educational_orientation")
    private Boolean educationalOrientation;
    @Column(name = "educational_orientation_years")
    private Integer educationalOrientationYears;
    @Column(name = "bilingual")
    private Boolean bilingual;
    @Size(max = 255)
    @Column(name = "languages")
    private String languages;
    @Size(max = 255)
    @Column(name = "home_languages")
    private String homeLanguages;
    @Column(name = "learning_lenguages")
    private Boolean learningLenguages;
    @Size(max = 255)
    @Column(name = "learning_lenguages_list")
    private String learningLenguagesList;
    @Size(max = 255)
    @Column(name = "place_years_residence")
    private String placeYearsResidence;
    @Size(max = 255)
    @Column(name = "place_years_residence_mother")
    private String placeYearsResidenceMother;
    @Size(max = 255)
    @Column(name = "place_years_residence_father")
    private String placeYearsResidenceFather;
    @Size(max = 255)
    @Column(name = "comments")
    private String comments;
    @Size(max = 255)
    @Column(name = "admin_comments")
    private String adminComments;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserGroupMap> userGroupMapList;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserTask> userTaskList;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public User(Integer idUser, String login, String password, boolean isAdmin) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getHearingLossDate() {
        return hearingLossDate;
    }

    public void setHearingLossDate(Date hearingLossDate) {
        this.hearingLossDate = hearingLossDate;
    }

    public String getCauseDeafnes() {
        return causeDeafnes;
    }

    public void setCauseDeafnes(String causeDeafnes) {
        this.causeDeafnes = causeDeafnes;
    }

    public String getDegreeDeafness() {
        return degreeDeafness;
    }

    public void setDegreeDeafness(String degreeDeafness) {
        this.degreeDeafness = degreeDeafness;
    }

    public String getDurationHearingLoss() {
        return durationHearingLoss;
    }

    public void setDurationHearingLoss(String durationHearingLoss) {
        this.durationHearingLoss = durationHearingLoss;
    }

    public Date getImplantationDate() {
        return implantationDate;
    }

    public void setImplantationDate(Date implantationDate) {
        this.implantationDate = implantationDate;
    }

    public String getCochlearImplantType() {
        return cochlearImplantType;
    }

    public void setCochlearImplantType(String cochlearImplantType) {
        this.cochlearImplantType = cochlearImplantType;
    }

    public String getCochlearImplantModel() {
        return cochlearImplantModel;
    }

    public void setCochlearImplantModel(String cochlearImplantModel) {
        this.cochlearImplantModel = cochlearImplantModel;
    }

    public Boolean getSignLanguage() {
        return signLanguage;
    }

    public void setSignLanguage(Boolean signLanguage) {
        this.signLanguage = signLanguage;
    }

    public Integer getSignLanguageYears() {
        return signLanguageYears;
    }

    public void setSignLanguageYears(Integer signLanguageYears) {
        this.signLanguageYears = signLanguageYears;
    }

    public Boolean getSpeechReading() {
        return speechReading;
    }

    public void setSpeechReading(Boolean speechReading) {
        this.speechReading = speechReading;
    }

    public Integer getSpeechReadingYears() {
        return speechReadingYears;
    }

    public void setSpeechReadingYears(Integer speechReadingYears) {
        this.speechReadingYears = speechReadingYears;
    }

    public Boolean getSpeechTherapy() {
        return speechTherapy;
    }

    public void setSpeechTherapy(Boolean speechTherapy) {
        this.speechTherapy = speechTherapy;
    }

    public Integer getSpeechTherapyYears() {
        return speechTherapyYears;
    }

    public void setSpeechTherapyYears(Integer speechTherapyYears) {
        this.speechTherapyYears = speechTherapyYears;
    }

    public Boolean getEducationalOrientation() {
        return educationalOrientation;
    }

    public void setEducationalOrientation(Boolean educationalOrientation) {
        this.educationalOrientation = educationalOrientation;
    }

    public Integer getEducationalOrientationYears() {
        return educationalOrientationYears;
    }

    public void setEducationalOrientationYears(Integer educationalOrientationYears) {
        this.educationalOrientationYears = educationalOrientationYears;
    }

    public Boolean getBilingual() {
        return bilingual;
    }

    public void setBilingual(Boolean bilingual) {
        this.bilingual = bilingual;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getHomeLanguages() {
        return homeLanguages;
    }

    public void setHomeLanguages(String homeLanguages) {
        this.homeLanguages = homeLanguages;
    }

    public Boolean getLearningLenguages() {
        return learningLenguages;
    }

    public void setLearningLenguages(Boolean learningLenguages) {
        this.learningLenguages = learningLenguages;
    }

    public String getLearningLenguagesList() {
        return learningLenguagesList;
    }

    public void setLearningLenguagesList(String learningLenguagesList) {
        this.learningLenguagesList = learningLenguagesList;
    }

    public String getPlaceYearsResidence() {
        return placeYearsResidence;
    }

    public void setPlaceYearsResidence(String placeYearsResidence) {
        this.placeYearsResidence = placeYearsResidence;
    }

    public String getPlaceYearsResidenceMother() {
        return placeYearsResidenceMother;
    }

    public void setPlaceYearsResidenceMother(String placeYearsResidenceMother) {
        this.placeYearsResidenceMother = placeYearsResidenceMother;
    }

    public String getPlaceYearsResidenceFather() {
        return placeYearsResidenceFather;
    }

    public void setPlaceYearsResidenceFather(String placeYearsResidenceFather) {
        this.placeYearsResidenceFather = placeYearsResidenceFather;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAdminComments() {
        return adminComments;
    }

    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }

    @XmlTransient
    public List<UserGroupMap> getUserGroupMapList() {
        return userGroupMapList;
    }

    public void setUserGroupMapList(List<UserGroupMap> userGroupMapList) {
        this.userGroupMapList = userGroupMapList;
    }

    @XmlTransient
    public List<UserTask> getUserTaskList() {
        return userTaskList;
    }

    public void setUserTaskList(List<UserTask> userTaskList) {
        this.userTaskList = userTaskList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coclear.entitys.User[ idUser=" + idUser + " ]";
    }
    
}
