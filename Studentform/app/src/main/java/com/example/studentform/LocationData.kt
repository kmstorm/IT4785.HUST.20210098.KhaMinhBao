package com.example.studentform

object LocationData {
    val cities = listOf("Chọn thành phố", "Hà Nội", "TP. Hồ Chí Minh")

    val districtsByCity = mapOf(
        "Hà Nội" to listOf("Quận Ba Đình", "Quận Hoàn Kiếm", "Quận Tây Hồ"),
        "TP. Hồ Chí Minh" to listOf("Quận 1", "Quận 3", "Quận 5")
    )

    val wardsByDistrict = mapOf(
        "Quận Ba Đình" to listOf("Phường Cống Vị", "Phường Điện Biên", "Phường Giảng Võ"),
        "Quận Hoàn Kiếm" to listOf("Phường Chương Dương", "Phường Hàng Bài", "Phường Hàng Bạc"),
        "Quận 1" to listOf("Phường Bến Nghé", "Phường Bến Thành", "Phường Nguyễn Thái Bình"),
        "Quận 3" to listOf("Phường Phạm Ngũ Lão", "Phường Võ Thị Sáu"),
        "Quận 5" to listOf("Phường 1", "Phường 2","Phường 2", "Phường 3", "Phường 8", "Phường 9", "Phường 10")
    )
}
