package com.evertonprdo.thirtydaysofsermononthemount

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.evertonprdo.thirtydaysofsermononthemount.model.Datasource
import com.evertonprdo.thirtydaysofsermononthemount.model.DayContent

@Composable
fun SermonOnTheMountScreen(modifier: Modifier = Modifier) {
    var showIntroduction: Boolean by rememberSaveable { mutableStateOf(true) }

    Box(modifier = modifier.fillMaxSize()) {
        if (showIntroduction) {
            DailyHeaderIntroduction(
                onHide = { showIntroduction = false },
                modifier = Modifier.zIndex(1f)
            )
        }
        DailyList(
            Datasource.dailyContentList,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DailyList(list: List<DayContent>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        items(list) {
            DayCard(it)
        }
    }
}

@Composable
fun DayCard(dayContent: DayContent, modifier: Modifier = Modifier) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    val heightAnim by animateDpAsState(
        targetValue = if (expanded) 200.dp else 120.dp,
    )

    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier.widthIn(max = 500.dp)
    ) {
        Image(
            painterResource(dayContent.imageResId),
            contentDescription = stringResource(dayContent.titleResId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(heightAnim)
                .fillMaxWidth()
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.day_label, dayContent.day),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        painter = painterResource(if (expanded) R.drawable.chevron_down else R.drawable.chevron_up),
                        contentDescription = stringResource(R.string.expand_button_content_description)
                    )
                }
            }

            if (expanded) {
                FoldableSection(
                    titleResId = dayContent.titleResId,
                    contentResId = dayContent.contentResId,
                    referenceRedId = dayContent.contentReferenceResId,
                )
            }
        }
    }
}

@Composable
fun FoldableSection(
    titleResId: Int,
    contentResId: Int,
    referenceRedId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(bottom = 8.dp)
    ) {
        Text(
            text = stringResource(titleResId),
            style = MaterialTheme.typography.headlineSmall,
        )

        Text(
            text = stringResource(contentResId),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )

        Text(
            text = stringResource(referenceRedId),
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic,
        )
    }
}

@Composable
fun DailyHeaderIntroduction(onHide: () -> Unit, modifier: Modifier = Modifier) {
    Dialog(
        onDismissRequest = onHide,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
                .background(
                    MaterialTheme.colorScheme.background,
                    RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.introduction_title),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.introduction_body_01),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
            )

            Text(
                text = stringResource(R.string.introduction_body_02),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.introduction_body_03),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
            Text(
                text = stringResource(R.string.introduction_body_04),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
            Text(
                text = stringResource(R.string.introduction_body_05),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Text(
                text = stringResource(R.string.introduction_body_06),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.introduction_body_07),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
            Text(
                text = stringResource(R.string.introduction_body_08),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
            Text(
                text = stringResource(R.string.introduction_body_09),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Text(
                text = stringResource(R.string.introduction_body_10),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )

            Button(
                onClick = onHide,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.btn_hide_introduction),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}