package com.arnab.criculture.models.fixturebyleague

import com.arnab.criculture.models.Links
import com.arnab.criculture.models.Meta

data class LeagueFixture(
    val `data`: List<LeagueFixtureData>,
    val links: Links,
    val meta: Meta
)