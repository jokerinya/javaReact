# Day 6 Assignment 2 - Database Model Design
## Acknowledgement
1. Today we are starting our HRMS system.

2. Model the database according to the data requirements. (Research: Adding tables and columns in PostgreSql) Your database name may be hrms.

3. Our system has three basic types of users. System personnel, Job Seekers and Employers. System staff: People working on your project. For example Kariyer.Net staff.

**IMPORTANT: I ​​want a database model in the first place here. I don't want you to write code.**

## Requirements
### Requirement 1: Job Seekers must be able to register in the system.

**Accept conditions:**
 * During registration, name, surname, TCNO, year of birth, e-mail, password, password repetition information is requested.
* All fields are required. The user is informed.
* Registering to the system by making the Mernis code.
* Authentication Unused user is informed.
* If there is a previously registered e-mail or TCNO, no registration will take place. The user is informed.
* Email verification is required for registration to occur.  



### Requirement 2: Employers must be able to register in the system.

**Accept conditions:**

* During registration, tracking company name, website, e-mail with the same domain as the website, telephone, password, password repetition information are requested. The aim here is to prevent non-companies from joining the system.
* All fields are required. The user is informed.
* The company name is as follows. Email verification is required for registration to occur. HRMS staff (ours :)) are over 25 years old.
* If there is a registered e-mail before, registration will not take place. The user is informed.  

### Request 3: The system should be able to add a general job position. For example Software Developer, Software Architect.

**Accept conditions:**

* These positions cannot be repeated. The user is warned.

## Solution Model
![Photo Of Model](./day6assignment2.jpg)

# Gun 6 Odev 2
## Bilgilendirme
1. Bugün HRMS sistemimize başlıyoruz.

2. Aşağıdaki isterlere göre veri tabanı modellemesi yapınız. (Araştırma: PostgreSql'de tablo ve kolon ekleme) Veritabanı isminiz hrms olabilir.

3. Sistemimiz üç temel kullanıcı türüne sahiptir. Sistem personeli, İş Arayanlar ve İş verenler. Sistem personeli : Projenizde çalışan kişiler. Örneğin Kariyer.Net personeli.

**ÖNEMLİ : Burada ilk etapta veri tabanı modeli istiyorum. Kod yazmanızı istemiyorum.**

## Gereklilikler
### Gereklilik 1 : İş Arayanlar sisteme kayıt olabilmelidir.

**Kabul Kriterleri:**

* Kayıt sırasında kullanıcıdan ad, soyad, tcno, doğum yılı, e-Posta, şifre, şifre tekrarı bilgileri istenir.
* Tüm alanlar zorunludur. Kullanıcı bilgilendirilir.
* Mernis doğrulaması yapılarak sisteme kayıt gerçekleştirilir.
* Doğrulama geçerli değilse kullanıcı bilgilendirilir.
* Daha önce kayıtlı bir e-posta veya tcno var ise kayıt gerçekleşmez. Kullanıcı bilgilendirilir.
* Kayıdın gerçekleşmesi için e-posta doğrulaması gerekir.

### Gereklilik 2 : İş verenler sisteme kayıt olabilmelidir.

**Kabul Kriterleri:**

* Kayıt sırasında kullanıcıdan şirket adı, web sitesi, web sitesi ile aynı domaine sahip e-posta, telefon, şifre, şifre tekrarı bilgileri istenir. Burada amaç sisteme şirket olmayanların katılmasını engellemektir.
* Tüm alanlar zorunludur. Kullanıcı bilgilendirilir.
* Şirket kayıtları iki şekilde doğrulanır. Kayıdın gerçekleşmesi için e-posta doğrulaması gerekir. HRMS personelinin (bizim :)) onayı gerekmektedir.
* Daha önce kayıtlı bir e-posta var ise kayıt gerçekleşmez. Kullanıcı bilgilendirilir.

### Gereklilik 3 : Sisteme genel iş pozisyonu isimleri eklenebilmelidir. Örneğin Software Developer, Software Architect.

**Kabul Kriterleri:**

* Bu pozisyonlar tekrar edemez. Kullanıcı uyarılır.