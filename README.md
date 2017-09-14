### Include both of these when you create your new gradle.properties file

    org.gradle.jvmargs=-Xmx1536m
    OpenWeatherAPIKey = "c109379d321012346a8b0e6bdea99879"


### JSON Data from Open Weather API

    DailyForecast (model)
    ——

    city name — “city” : “name”  / city.name
    country name — “city” : “country”  / city.country
    coordinates — “coord” : “lon”  & “coord” : “lat”   / city.coord.lon  & city.coord.lat

    date — “dt” (unix time)  / list.dt
    low temp — “temp” : “min”   (kelvins)   /  list.temp.min
    high temp — “temp” : “max”   /  list.temp.max
    humidity % — “humidity”   /  list.humidity
    **might want to also include —— list.weather.main — in addition to description
    conditions  — “weather” : “description”  (example: “sky is clear” or “moderate rain”)    /   list.weather.description
    atmospheric pressure — “pressure”   /   list.pressure

    wind speed — “speed”  (meters/sec)  /  list.speed
    cloudiness percentage — “clouds”     /   list.clouds