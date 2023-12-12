package App;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import observer.observer;
import observer.SMSobserver;
import subject.messageSubject;
import subject.subject;
public class matchFinderApp {
    private static final Map<String, String> leaguesAndCountries = new HashMap<>();

    static {
        leaguesAndCountries.put("Pro League", "Saudi Arabia");
        leaguesAndCountries.put("Ligue 1", "France");
        leaguesAndCountries.put("Premier League", "England");
        leaguesAndCountries.put("Serie A", "Italy");
        leaguesAndCountries.put("La Liga", "Spain");
        leaguesAndCountries.put("Bundesliga", "Germany");
        leaguesAndCountries.put("Primeira Liga", "Portugal");
        leaguesAndCountries.put("CAF Champions League", "World");
        leaguesAndCountries.put("UEFA Champions League", "World");
        leaguesAndCountries.put("AFC Champions League", "World");

    }

    public static void main(String[] args) {
        observer SMSobserver = new SMSobserver("489489418894");
        subject s = new messageSubject();
        s.addSubscriber(SMSobserver);
        String responseBody = fetchMatchesForToday();
        if (responseBody != null) {
            Map<String, String> leaguesMatches = parseMatches(responseBody);


            String matchesInfo = getLeagueMatchesString(leaguesMatches);
          //  s.notifySubscribers(matchesInfo); // This will send an SMS message (There is problem, not in the code but the api that send the message)
          System.out.println(matchesInfo); // This will print League Matches in command line

        }

    }

    private static String fetchMatchesForToday() {
        String todayDate = LocalDate.now().toString(); // Format: YYYY-MM-DD
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api-football-v1.p.rapidapi.com/v3/fixtures?date=" + todayDate))
                    .header("X-RapidAPI-Key", "587dfe77b5mshb2aeef60e294ab7p1c2eaajsn5bccd7af211e")
                    .header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Response status code: " + response.statusCode());
                return response.body();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    private static Map<String, String> parseMatches(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray fixtures = jsonObject.getJSONArray("response");
        Map<String, String> leagueMatches = new HashMap<>();

        for (int i = 0; i < fixtures.length(); i++) {
            JSONObject fixture = fixtures.getJSONObject(i);


            JSONObject league = fixture.getJSONObject("league");
            String leagueName = league.getString("name");
            String country = league.getString("country");


            if (leaguesAndCountries.containsKey(leagueName) && leaguesAndCountries.get(leagueName).equals(country)) {
                JSONObject teams = fixture.getJSONObject("teams");
                String homeTeamName = teams.getJSONObject("home").getString("name");
                String awayTeamName = teams.getJSONObject("away").getString("name");


                JSONObject venue = fixture.getJSONObject("fixture").getJSONObject("venue");
                String venueCity = venue.getString("city");
                String venueName = venue.getString("name");


                int homeScore = -1;
                int awayScore = -1;
                if (!fixture.isNull("score") && !fixture.getJSONObject("score").isNull("fulltime")) {
                    JSONObject score = fixture.getJSONObject("score").getJSONObject("fulltime");
                    homeScore = score.optInt("home", -1);
                    awayScore = score.optInt("away", -1);
                }


                String leagueKey = "Country: " + country + " League: " + leagueName;

                String matchInfo;
                if (homeScore != -1 && awayScore != -1) {
                    matchInfo = String.format(
                            "Venue: %s, %s, Home Team: %s, Away Team: %s, Score: %s-%s",
                            venueCity, venueName,homeTeamName, awayTeamName, homeScore, awayScore
                    );
                } else {
                    matchInfo = String.format(
                            "Venue: %s, %s, Home Team: %s, Away Team: %s, Score: N/A",
                            venueCity, venueName,homeTeamName, awayTeamName
                    );
                }

                leagueMatches.merge(leagueKey, matchInfo, (matches, newMatch) -> matches + "\n" + newMatch);


            }
        }

        return leagueMatches;
    }


    private static String getLeagueMatchesString(Map<String, String> leagueMatches) {
        StringBuilder matchesBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : leagueMatches.entrySet()) {
            matchesBuilder.append(entry.getKey()).append("\n").append(entry.getValue()).append("\n\n");
        }

        return matchesBuilder.toString();
    }

}
