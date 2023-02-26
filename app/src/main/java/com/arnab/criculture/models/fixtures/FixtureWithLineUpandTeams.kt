package com.arnab.criculture.models.fixtures

import com.arnab.criculture.models.Links
import com.arnab.criculture.models.Meta

data class FixtureWithLineUpandTeams(
    val `data`: List<FixtureData>,
    val links: Links,
    val meta: Meta
)