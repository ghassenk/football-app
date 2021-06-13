package com.gk.app.footballapp

object TestData {
    // We use content descriptions to check images and real api content.
    // (for frequently changing API content we would probably use a mock api)
    val expectedSearchResultDescriptions = arrayListOf(
        "Angers", "Bordeaux", "Brest", "Clermont Foot", "Lens", "Lille", "Lorient", "Lyon",
        "Marseille", "Metz", "Monaco", "Montpellier", "Nantes", "Nice", "Paris SG", "Rennes",
        "St Etienne", "Stade de Reims", "Strasbourg", "Troyes",
    )
    val testFakeLeagueName = "bla bla"// we assume that this league does not exist
    val testFakeTeamName = "bla bla"
    val testLeagueName = "French Ligue 1"
    val testClickTeamName = "Paris SG"
    val testRealTeamName = "Paris SG"
    val testCountryName = "France"
    val testClickPosition = 14

    // For simplification we will use only the beginning of the description
    val expectedTeamDescription =
        "Paris Saint-Germain Football Club, commonly referred to " +
                "as Paris Saint-Germain, Paris SG, or simply Paris or PSG, is a French professional " +
                "football club based in Paris. Founded in 1970,"

    val testAutoCompleteKeyWord = "French"
    val expectedAutoCompleteWords = arrayListOf(
        "French Ligue 1", "French Ligue 2", "French LNB", "French Top 14", "French LNH Division 1",
        "French LNB Pro B", // for simplicity we're not looking for exhaustive list
    )
}