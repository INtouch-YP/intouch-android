package care.intouch.app.feature.home.data.models

import kotlinx.serialization.Serializable

@Serializable
enum class ClarifyingEmotionDto(name: String) {
    Loss(name = "Loss"),
    Fear(name = "Fear"),
    Guilt(name = "Guilt"),
    Laziness(name = "Laziness"),
    Interest(name = "Interest"),
    Pride(name = "Pride"),
    Hope(name = "Hope"),
    Hapiness(name = "Hapiness"),
    Anger(name = "Anger"),
    Anxiety(name = "Anxiety"),
    Embarrassment(name = "Embarrassment"),
    Calmness(name = "Calmness"),
    Inspiration(name = "Inspiration"),
    Humility(name = "Humility"),
    Nervousness(name = "Nervousness"),
    Depression(name = "Depression"),
    Shame(name = "Shame"),
    Amazement(name = "Amazement"),
    Euphoria(name = "Euphoria"),
    Disgust(name = "Disgust"),
    Sadness(name = "Sadness"),
    Joy(name = "Joy"),
    Respect(name = "Respect"),
    Exhaustion(name = "Exhaustion"),
    Impatience(name = "Impatience"),
    Excitement(name = "Excitement"),
    Loneliness(name = "Loneliness"),
    Frustration(name = "Frustration"),
    Acceptance(name = "Acceptance"),
    Enthusiasm(name = "Enthusiasm"),
    Love(name = "Love"),
    Dissapointment(name = "Dissapointment"),
    Confusion(name = "Confusion"),
    Satisfaction(name = "Satisfaction"),
    SelfLove(name = "Self-love"),
    Gratitude(name = "Gratitude")
}