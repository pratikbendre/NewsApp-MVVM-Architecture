package com.pratikbendre.newsapp.ui.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pratikbendre.newsapp.R
import com.pratikbendre.newsapp.data.model.ArticleModel
import com.pratikbendre.newsapp.data.model.SourceModel

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val contentDesc = stringResource(id = R.string.loading)
        CircularProgressIndicator(modifier = Modifier
            .align(Alignment.Center)
            .semantics {
                contentDescription = contentDesc
            })
    }
}

@Composable
fun ShowError(onClick: () -> Unit, text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center) // Centers the Column in the Box
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.error),
                contentDescription = null, // Use null for decorative images
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier.wrapContentSize()
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Oops,",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Red,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Something went wrong. Let's try one more time.",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            RetryButton(onClick = onClick, title = "Retry") // Ensure you define the RetryButton
        }
    }

}

@Composable
fun ShowArticle(articleModel: ArticleModel, onNewsClick: (url: String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            if (articleModel.url.isNotEmpty()) {
                onNewsClick(articleModel.url)
            }
        }) {
        BannerImage(articleModel)
        TitleText(articleModel.title)
        DescriptionText(articleModel.description)
        SourceText(articleModel.sourceModel)
    }
}

@Composable
fun BannerImage(articleModel: ArticleModel) {
    AsyncImage(
        model = articleModel.imageurl, contentDescription = articleModel.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
}

@Composable
fun TitleText(title: String) {
    if (title.isNotEmpty()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun DescriptionText(description: String?) {
    if (!description.isNullOrEmpty()) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun SourceText(sourceModel: SourceModel) {
    Text(
        text = sourceModel.name,
        style = MaterialTheme.typography.titleSmall,
        color = Color.Gray,
        maxLines = 1,
        modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
    )
}

@Composable
fun SimpleButton(onClick: () -> Unit, title: String) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),  // Rounded corners
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), // Padding around the button
        elevation = ButtonDefaults.buttonElevation(6.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun RetryButton(onClick: () -> Unit, title: String) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),  // Rounded corners
        modifier = Modifier
            .fillMaxWidth(), // Padding around the button
        elevation = ButtonDefaults.buttonElevation(6.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}