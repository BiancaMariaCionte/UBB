package domain;

public class Weather {
    private Integer startHour;
    private Integer endHour;
    private Integer temperature;
    private Integer precipitationProbability;
    private String description;

    @Override
    public String toString() {
        return "Weather{" +
                "startHour=" + startHour +
                ", endHour=" + endHour +
                ", temperature=" + temperature +
                ", precipitationProbability=" + precipitationProbability +
                ", description='" + description + '\'' +
                '}';
    }

    public Weather(Integer startHour, Integer endHour, Integer temperature, Integer precipitationProbability, String description) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.temperature = temperature;
        this.precipitationProbability = precipitationProbability;
        this.description = description;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(Integer precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
