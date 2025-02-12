package com.evertonprdo.superheroes

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evertonprdo.superheroes.heroes.model.Hero
import com.evertonprdo.superheroes.heroes.model.HeroesRepository
import com.evertonprdo.superheroes.ui.theme.Shapes

@Composable
fun SuperheroList(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        items(HeroesRepository.heroes) {
            SuperheroCard(hero = it)
        }
    }
}

@Composable
fun SuperheroCard(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        shape = Shapes.large,
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .height(72.dp)
                .fillMaxWidth()
        ) {
            SuperheroCardInfo(
                nameResId = hero.nameRes,
                descriptionResId = hero.descriptionRes,
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(hero.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
fun SuperheroCardInfo(
    @StringRes nameResId: Int,
    @StringRes descriptionResId: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(nameResId),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(descriptionResId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}