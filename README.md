# ClubIoT
Web part of IoT PGO6
## DJ page
URL
```
/djpage
```
> haalt alle songs op uit de MySQL database

API Calls:

```
/api/search?song={songtitle}
```
> zoekt een bepaald lied in de MySQL database
```
/api/play/{id}
```
> haalt dit lied op uit de database, stuurt het lied op als json naar de MQTT broker
made 


Frontend: Yunus Emre Yigit

Backend: Ruben De Swaef
## Dashboard page
URL
```
/dashboard
```
> Laad data uit database van alle, met populairste en minst populaire, liedjes & beste voter

API Calls:

    /api/songs

> Laat alle liedjes zien uit de MySQL database

    /api/votes/{id}
    
> Laat liedje zien met aantal votes per seconde (Nog niet volledig af)


    /api/refresh
    
> Laat de recent verwerkte data over meest populaire en minst populaire lied & beste voter zien

Frontend: Stijn Dirickx

Backend: Vadim Gouskov