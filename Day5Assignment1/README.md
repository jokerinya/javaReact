# Day 5 Assignment 1

## Requirements

1. Below, I have written the required requirements and acceptance criteria for registration and login in an e-commerce system. We want to write a Java backend for this system.

2. Users must be able to become a member by entering their information into the system.

    * It should be possible to subscribe to the system with basic user information, e-mail and password. 
    * Basic user information: first name, last name, e-mail, password. 
    * All basic information is required.
    * The password must be at least 6 characters.
    * The e-mail field must be in e-mail format. (Do it with Regex. Research subject)
    * The e-mail must not have been used before.
    * Name and surname must contain at least two characters.
    * A confirmation e-mail should be sent to the user as a result of the membership. (Simulation)
    * Membership must be completed when the verification link is clicked. (Simulation)
    * In case of failure or success, the user should be informed.

3. Users must be able to subscribe to the system with their Google accounts. (Simulation)

    * Other authorization services can be used in the future. (Configure the system to integrate external services.)
    * In case of failure or success, the user should be informed.

4. Users should be able to log into the system with their e-mail and password information.

    * Email and password are required
    * In case of failure or success, the user should be informed.

## Solution
1. Solution codes can be found on project structure.
2. Here is the picture of the structure.
![Photo Of ProjectStructure](./day5assignment1.jpg)

# Gun 5 Odev 1

## Gereksinimler

1. Aşağıda bir e-ticaret sisteminde sisteme kayıt ve sisteme giriş için gerekli gereksinim ve kabul kriterlerini yazdım. Bu sisteme ait Java backend yazmak istiyoruz.

2. Kullanıcılar sisteme bilgilerini girerek üye olabilmelidir.

    * Sisteme temel kullanıcı bilgileri , e-posta ve parolayla üye olunabilmelidir. 
    * Temel kullanıcı bilgileri : ad, soyad, e-posta, parola. 
    * Temel bilgilerin tamamı zorunludur.
    * Parola en az 6 karakterden oluşmalıdır.
    * E-posta alanı e-posta formatında olmalıdır. (Regex ile yapınız. Araştırma konusu)
    * E-Posta daha önce kullanılmamış olmalıdır.
    * Ad ve soyad en az iki karakterden oluşmalıdır.
    * Üyelik sonucu kullanıcıya doğrulama e-postası gönderilmelidir. (Simulasyon)
    * Doğrulama linki tıklandığında üyelik tamamlanmalıdır. (Simulasyon)
    * Hatalı veya başarılı durumda kullanıcı bilgilendirilmelidir.

3. Kullanıcılar sisteme Google hesapları ile üye olabilmelidir. (Simulasyon)

    * İlerleyen zamanlarda başka yetkilendirme servisleri de kullanılabilir. (Sistemi dış servis entegrasyonu olacak şekilde yapılandırınız.)
    * Hatalı veya başarılı durumda kullanıcı bilgilendirilmelidir.

4. Kullanıcılar e-posta ve parola bilgisiyle sisteme giriş yapabilmelidir.

    * E-posta ve parola zorunludur
    * Hatalı veya başarılı durumda kullanıcı bilgilendirilmelidir.