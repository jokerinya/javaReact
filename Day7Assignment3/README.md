# Day 7 Assignment 3

## Requirements

- Before modeling, put the following requirements into the code.

- ** IMPORTANT 1: Create fake services for verification. For example, you don't really need to send mail. **

- ** IMPORTANT 2: Do not code beyond the requirements. This is a software developer behavior against Project management and ALM (Application LifeCycle Management) rules. **

1. Req 1: Job Seekers must be able to register to the system.

   _Accept conditions:_

   - During registration, the user is asked for name, surname, tcno, year of birth, e-mail, password, password repetition information.
   - All fields are required. The user is informed.
   - Mernis verification is done and the system is registered.
   - If the verification is not valid, the user is informed.
   - If there is a previously registered e-mail or tcno, no registration will be made. The user is informed.
   - Email verification is required for registration to occur.

2. Req 2: Employers should be able to register in the system.

   _Accept conditions:_

   - During the registration, the user is asked for company name, website, e-mail, phone, password, password repeat information with the same domain as the website.
   - The aim here is to prevent non-companies from joining the system.
   - All fields are required. The user is informed.
   - Company records are verified in two ways. Email verification is required for registration to occur. Approval from HRMS staff (our :) is required.
   - If there is a registered e-mail before, registration will not take place. The user is informed.

3. Req 3: General job position names should be added to the system. For example Software Developer, Software Architect.

   _Accept conditions:_

   - These positions cannot be repeated. The user is warned.

4. Req 4: Employers should be able to be listed. (Entire list only)

5. Req 5: Job seekers should be able to be listed. (Entire list only)

6. Req 6: Job positions should be listed. (Entire list only)

## Solution ScreenShots and Project Link

- Apis and Controllers

![SS of SwaggerUI-1](swagger-1.JPG)

- Project Structure

![SS of SwaggerUI-2](structure-1.jpg)

- DB Model

![DB Model](db-1.jpg)

- [HRMS Project](https://github.com/jokerinya2013/javaReact/tree/master/hrms)

# Gun 7 Odev 3

## Gereklilikler

- Daha ??nce modellemesini yapt??????n??z a??a????daki gereksinimleri koda d??k??n??z.

- **??NEML?? 1 : Do??rulama i??lemleri i??in sahte servisler olu??turunuz. ??rne??in, ger??ekten mail g??ndermeniz gerekmez.**

- **??NEML?? 2 : Gereksinimler d??????nda kodlama yapmay??n??z. Bu, Proje y??netimi ve ALM (Application LifeCycle Management) kurallar??na ayk??r?? bir yaz??l??m geli??tirici davran??????d??r.**

1. Req 1 : ???? Arayanlar sisteme kay??t olabilmelidir.

   _Kabul Kriterleri:_

   - Kay??t s??ras??nda kullan??c??dan ad, soyad, tcno, do??um y??l??, e-Posta, ??ifre, ??ifre tekrar?? bilgileri istenir.
   - T??m alanlar zorunludur. Kullan??c?? bilgilendirilir.
   - Mernis do??rulamas?? yap??larak sisteme kay??t ger??ekle??tirilir.
   - Do??rulama ge??erli de??ilse kullan??c?? bilgilendirilir.
   - Daha ??nce kay??tl?? bir e-posta veya tcno var ise kay??t ger??ekle??mez. Kullan??c?? bilgilendirilir.
   - Kay??d??n ger??ekle??mesi i??in e-posta do??rulamas?? gerekir.

2. Req 2 : ???? verenler sisteme kay??t olabilmelidir.

   _Kabul Kriterleri:_

   - Kay??t s??ras??nda kullan??c??dan ??irket ad??, web sitesi, web sitesi ile ayn?? domaine sahip e-posta, telefon, ??ifre, ??ifre tekrar?? bilgileri istenir.
   - Burada ama?? sisteme ??irket olmayanlar??n kat??lmas??n?? engellemektir.
   - T??m alanlar zorunludur. Kullan??c?? bilgilendirilir.
   - ??irket kay??tlar?? iki ??ekilde do??rulan??r. Kay??d??n ger??ekle??mesi i??in e-posta do??rulamas?? gerekir. HRMS personelinin (bizim :)) onay?? gerekmektedir.
   - Daha ??nce kay??tl?? bir e-posta var ise kay??t ger??ekle??mez. Kullan??c?? bilgilendirilir.

3. Req 3 : Sisteme genel i?? pozisyonu isimleri eklenebilmelidir. ??rne??in Software Developer, Software Architect.

   _Kabul Kriterleri:_

   - Bu pozisyonlar tekrar edemez. Kullan??c?? uyar??l??r.

4. Req 4 : ???? verenler listelenebilmelidir. (Sadece t??m liste)

5. Req 5 : ???? arayanlar listelenebilmelidir. (Sadece t??m liste)

6. Req 6 : ???? pozisyonlar?? listelenebilmelidir. (Sadece t??m liste)
