package com.example.matuleclothes.Widget

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matuleclothes.domain.model.Projects
import com.example.network.domain.usecase.GetProjectsUseCase
import com.example.network.domain.usecase.LoadTokenUseCase
import com.example.network.domain.usecase.LoadUserIdUseCase
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Buttons.Small.SmallActiveButton
import com.example.uikit.Presentation.CardStroke
import com.example.uikit.Presentation.Placeholder
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MyAppWidget(
    private val getProjectsUseCase: GetProjectsUseCase,
    private val loadUserIdUseCase: LoadUserIdUseCase,
    private val loadTokenUseCase: LoadTokenUseCase
) : GlanceAppWidget() {

    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        val token = loadTokenUseCase.invoke()
        val userId = loadUserIdUseCase.invoke()
        val project = getProjectsUseCase.invoke().items.find { it.title == "аыаы" }
        Log.i("userId, project", userId + project)
        provideContent {
            if(token.isNotEmpty()) {
                if (project != null) {
                    WidgetContent(
                        Projects(
                            id = project.id,
                            title = project.title,
                            dateStart = project.dateStart
                        )
                    )
                } else {
                    Text("Нет данных")
                }
            } else{
                Box(modifier = GlanceModifier.background(color = White)) {
                    Text("Авторизуйтесь в приложении")
                }
            }
        }
    }
}

@Composable
fun WidgetContent(projects: Projects) {
    Column(
        modifier = GlanceModifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(White)
            .cornerRadius(12.dp)
            .padding(16.dp)
    ) {
        Text(
            text = projects.title,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = ColorProvider(Color.Black)
            )
        )

        Spacer(modifier = GlanceModifier.height(44.dp))

        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Horizontal.Start,
            verticalAlignment = Alignment.Vertical.CenterVertically
        ) {

            Text(
                text = "Прошло ${projects.dateStart} дня",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = ColorProvider(Placeholder)
                ),
                modifier = GlanceModifier.defaultWeight()
            )

            Button(
                text = "Открыть",
                onClick = {}
            )
        }
    }
}

@AndroidEntryPoint
class MyAppWidgetReceiver() : GlanceAppWidgetReceiver() {
    @Inject
    lateinit var getProjectsUseCase: GetProjectsUseCase

    @Inject
    lateinit var loadUserIdUseCase: LoadUserIdUseCase

    @Inject
    lateinit var loadTokenUseCase: LoadTokenUseCase
    override val glanceAppWidget: GlanceAppWidget
        get() = MyAppWidget(
            getProjectsUseCase,
            loadUserIdUseCase,
            loadTokenUseCase
        )
}