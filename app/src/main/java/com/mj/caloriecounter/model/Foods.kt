package com.mj.caloriecounter.model

val foods = listOf(
    // پروتئین ها
    Food("سینه مرغ", 165.0, 31.0, 0.0, 3.6, listOf(MeasurementUnit("عدد متوسط", 200))),
    Food("ران مرغ", 209.0, 26.0, 0.0, 11.0, listOf(MeasurementUnit("عدد متوسط", 150))),
    Food("گوشت گوساله", 250.0, 26.0, 0.0, 15.0, listOf(MeasurementUnit("تکه خورشتی", 30))),
    Food("گوشت چرخ کرده", 276.0, 25.0, 0.0, 20.0, listOf(MeasurementUnit("قاشق غذاخوری پخته", 30))),
    Food("ماهی سالمون", 208.0, 20.0, 0.0, 13.0, listOf(MeasurementUnit("فیله متوسط", 150))),
    Food(
        "تن ماهی", 132.0, 28.0, 0.0, 1.0, listOf(
            MeasurementUnit("عدد ۱۸۰ گرمی", 180),
            MeasurementUnit("عدد ۱۲۰ گرمی", 120)
        )
    ),
    Food("تخم مرغ آبپز", 155.0, 13.0, 1.1, 11.0, listOf(MeasurementUnit("عدد", 50))),
    Food("سفیده تخم مرغ آبپز", 52.0, 11.0, 0.7, 0.2, listOf(MeasurementUnit("عدد", 33))),

    // لبنیات
    Food("شیر کم چرب", 42.0, 3.4, 5.0, 1.0, listOf(MeasurementUnit("لیوان", 240))),
    Food("ماست کم چرب", 60.0, 5.0, 7.0, 1.5, listOf(MeasurementUnit("لیوان", 240), MeasurementUnit("قاشق غذاخوری", 20))),
    Food("ماست یونانی", 73.0, 10.0, 4.0, 0.4, listOf(MeasurementUnit("لیوان", 240), MeasurementUnit("قاشق غذاخوری", 20))),
    Food("پنیر سفید", 264.0, 14.0, 4.0, 21.0, listOf(MeasurementUnit("قوطی کبریت", 30))),
    Food("پنیر کم چرب", 180.0, 20.0, 3.0, 10.0, listOf(MeasurementUnit("قوطی کبریت", 30))),

    // میوه ها
    Food("موز", 89.0, 1.1, 23.0, 0.3, listOf(MeasurementUnit("عدد کوچک", 80), MeasurementUnit("عدد متوسط", 120), MeasurementUnit("عدد بزرگ", 150))),
    Food("سیب", 52.0, 0.3, 14.0, 0.2, listOf(MeasurementUnit("عدد کوچک", 100), MeasurementUnit("عدد متوسط", 150), MeasurementUnit("عدد بزرگ", 220))),
    Food("پرتقال", 47.0, 0.9, 12.0, 0.1, listOf(MeasurementUnit("عدد کوچک", 90), MeasurementUnit("عدد متوسط", 130), MeasurementUnit("عدد بزرگ", 180))),
    Food("توت فرنگی", 32.0, 0.7, 7.7, 0.3, listOf(MeasurementUnit("عدد کوچک", 7), MeasurementUnit("عدد متوسط", 12), MeasurementUnit("عدد بزرگ", 18))),
    Food("خرما", 282.0, 2.5, 75.0, 0.4, listOf(MeasurementUnit("عدد کوچک", 5), MeasurementUnit("عدد متوسط", 8), MeasurementUnit("عدد بزرگ", 12))),
    Food("انگور", 69.0, 0.7, 18.0, 0.2, listOf(MeasurementUnit("خوشه کوچک", 50), MeasurementUnit("خوشه متوسط", 100), MeasurementUnit("خوشه بزرگ", 180))),
    Food("هندوانه", 30.0, 0.6, 7.6, 0.2, listOf(MeasurementUnit("قاچ کوچک", 100), MeasurementUnit("قاچ متوسط", 200), MeasurementUnit("قاچ بزرگ", 400))),
    Food("خربزه", 34.0, 0.8, 8.2, 0.2, listOf(MeasurementUnit("قاچ کوچک", 100), MeasurementUnit("قاچ متوسط", 200), MeasurementUnit("قاچ بزرگ", 400))),
    Food("کیوی", 61.0, 1.1, 15.0, 0.5, listOf(MeasurementUnit("عدد کوچک", 50), MeasurementUnit("عدد متوسط", 75), MeasurementUnit("عدد بزرگ", 100))),
    Food("هلو", 39.0, 0.9, 10.0, 0.3, listOf(MeasurementUnit("عدد کوچک", 100), MeasurementUnit("عدد متوسط", 150), MeasurementUnit("عدد بزرگ", 200))),
    Food("گلابی", 57.0, 0.4, 15.0, 0.1, listOf(MeasurementUnit("عدد کوچک", 120), MeasurementUnit("عدد متوسط", 160), MeasurementUnit("عدد بزرگ", 230))),
    Food("آناناس", 50.0, 0.5, 13.0, 0.1, listOf(MeasurementUnit("اسلایس کوچک", 50), MeasurementUnit("اسلایس متوسط", 80), MeasurementUnit("اسلایس بزرگ", 120))),
    Food("انبه", 60.0, 0.8, 15.0, 0.4, listOf(MeasurementUnit("عدد کوچک", 150), MeasurementUnit("عدد متوسط", 200), MeasurementUnit("عدد بزرگ", 300))),

    // آجیل و مغزها
    Food("بادام", 579.0, 21.0, 22.0, 50.0, listOf(MeasurementUnit("عدد", 1), MeasurementUnit("لیوان", 140))),
    Food("گردو", 654.0, 15.0, 14.0, 65.0, listOf(MeasurementUnit("عدد مغز کامل", 5), MeasurementUnit("لیوان", 100))),
    Food("بادام زمینی", 567.0, 26.0, 16.0, 49.0, listOf(MeasurementUnit("عدد", 1), MeasurementUnit("لیوان", 145))),
    Food("فندق", 628.0, 15.0, 17.0, 61.0, listOf(MeasurementUnit("عدد", 1), MeasurementUnit("لیوان", 135))),
    Food("پسته", 562.0, 20.0, 28.0, 45.0, listOf(MeasurementUnit("عدد", 1), MeasurementUnit("لیوان", 120))),
    Food("کره بادام زمینی", 588.0, 25.0, 20.0, 50.0, listOf(MeasurementUnit("قاشق غذاخوری", 16), MeasurementUnit("قاشق چای‌خوری", 5))),

    // غلات
    Food("برنج سفید پخته", 130.0, 2.7, 28.0, 0.3, listOf(MeasurementUnit("قاشق غذاخوری", 20), MeasurementUnit("کفگیر", 120))),
    Food("برنج قهوه‌ای پخته", 111.0, 2.6, 23.0, 0.9, listOf(MeasurementUnit("قاشق غذاخوری", 20), MeasurementUnit("کفگیر", 120))),
    Food("سیب زمینی پخته", 87.0, 1.9, 20.0, 0.1, listOf(MeasurementUnit("عدد متوسط", 150), MeasurementUnit("لیوان", 150))),
    Food("سیب زمینی تنوری", 93.0, 2.5, 21.0, 0.1, listOf(MeasurementUnit("عدد متوسط", 150), MeasurementUnit("لیوان", 150))),
    Food("سیب زمینی سرخ کرده", 312.0, 3.4, 41.0, 15.0, listOf(MeasurementUnit("پرس کوچک", 100), MeasurementUnit("لیوان", 85))),
    Food("پوره سیب زمینی", 88.0, 2.0, 15.0, 3.0, listOf(MeasurementUnit("قاشق غذاخوری", 30), MeasurementUnit("لیوان", 210))),
    Food("جو دوسر", 389.0, 16.9, 66.0, 6.9, listOf(MeasurementUnit("قاشق غذاخوری", 10), MeasurementUnit("لیوان", 80))),
    Food("ماکارونی پخته", 158.0, 5.8, 31.0, 0.9, listOf(MeasurementUnit("لیوان", 140), MeasurementUnit("قاشق غذاخوری", 20))),
    Food("اسپاگتی پخته", 158.0, 5.8, 31.0, 0.9, listOf(MeasurementUnit("لیوان", 140), MeasurementUnit("قاشق غذاخوری", 20))),
    Food("ذرت پخته", 96.0, 3.4, 21.0, 1.5, listOf(MeasurementUnit("قاشق غذاخوری", 15), MeasurementUnit("لیوان", 145))),

    // نان‌ها
    Food("نان سنگک", 259.0, 8.7, 54.0, 1.2, listOf(MeasurementUnit("کف دست", 30))),
    Food("نان بربری", 265.0, 8.5, 55.0, 1.5, listOf(MeasurementUnit("کف دست", 30))),
    Food("نان تافتون", 287.0, 8.0, 60.0, 1.0, listOf(MeasurementUnit("کف دست", 15), MeasurementUnit("قرص کامل", 120))),
    Food("نان لواش", 290.0, 8.0, 61.0, 1.2, listOf(MeasurementUnit("قرص کامل", 25), MeasurementUnit("کف دست", 10))),
    Food("نان تست سفید", 266.0, 8.9, 49.0, 3.2, listOf(MeasurementUnit("اسلایس", 25))),
    Food("نان تست سبوس‌دار", 247.0, 13.0, 41.0, 4.2, listOf(MeasurementUnit("اسلایس", 25))),
    Food("نان باگت", 272.0, 8.5, 57.0, 1.7, listOf(MeasurementUnit("عدد کامل", 75), MeasurementUnit("تکه ۱۰ سانتی", 25))),
    Food("نان همبرگر", 270.0, 9.0, 45.0, 5.0, listOf(MeasurementUnit("عدد", 60))),

    // حبوبات
    Food("عدس پخته", 116.0, 9.0, 20.0, 0.4, listOf(MeasurementUnit("قاشق غذاخوری", 15), MeasurementUnit("لیوان", 180))),
    Food("لوبیا قرمز پخته", 127.0, 8.7, 23.0, 0.5, listOf(MeasurementUnit("قاشق غذاخوری", 15), MeasurementUnit("لیوان", 180))),
    Food("لوبیا چیتی پخته", 143.0, 9.0, 26.0, 0.7, listOf(MeasurementUnit("قاشق غذاخوری", 15), MeasurementUnit("لیوان", 180))),
    Food("نخود پخته", 164.0, 8.9, 27.0, 2.6, listOf(MeasurementUnit("قاشق غذاخوری", 15), MeasurementUnit("لیوان", 180))),
    Food("لپه پخته", 118.0, 8.3, 21.0, 0.4, listOf(MeasurementUnit("قاشق غذاخوری", 15), MeasurementUnit("لیوان", 180))),

    // سبزیجات
    Food("خیار", 15.0, 0.7, 3.6, 0.1, listOf(MeasurementUnit("عدد متوسط", 100))),
    Food("گوجه فرنگی", 18.0, 0.9, 3.9, 0.2, listOf(MeasurementUnit("عدد متوسط", 120))),
    Food("کاهو", 15.0, 1.4, 2.9, 0.2, listOf(MeasurementUnit("برگ متوسط", 15))),
    Food("کلم بروکلی", 34.0, 2.8, 6.6, 0.4, listOf(MeasurementUnit("لیوان خرد شده", 90), MeasurementUnit("تکه متوسط", 150))),
    Food("هویج", 41.0, 0.9, 10.0, 0.2, listOf(MeasurementUnit("عدد متوسط", 60))),
    Food("اسفناج", 23.0, 2.9, 3.6, 0.4, listOf(MeasurementUnit("لیوان خرد شده", 30))),
    Food("قارچ", 22.0, 3.1, 3.3, 0.3, listOf(MeasurementUnit("عدد متوسط", 20))),
    Food("پیاز", 40.0, 1.1, 9.3, 0.1, listOf(MeasurementUnit("عدد متوسط", 110))),

    // چربی‌ها و افزودنی‌ها
    Food("روغن زیتون", 884.0, 0.0, 0.0, 100.0, listOf(MeasurementUnit("قاشق غذاخوری", 14), MeasurementUnit("قاشق چای‌خوری", 5), MeasurementUnit("لیوان", 216))),
    Food("کره", 717.0, 0.9, 0.1, 81.0, listOf(MeasurementUnit("قاشق غذاخوری", 14), MeasurementUnit("قاشق چای‌خوری", 5), MeasurementUnit("بسته کوچک رستورانی", 10), MeasurementUnit("لیوان", 225))),
    Food("ارده", 595.0, 17.0, 21.0, 54.0, listOf(MeasurementUnit("قاشق غذاخوری", 15), MeasurementUnit("لیوان", 240))),
    Food("عسل", 304.0, 0.3, 82.0, 0.0, listOf(MeasurementUnit("قاشق غذاخوری", 21), MeasurementUnit("قاشق چای‌خوری", 7), MeasurementUnit("لیوان", 340))),
    Food("مربا", 278.0, 0.3, 69.0, 0.1, listOf(MeasurementUnit("قاشق غذاخوری", 20), MeasurementUnit("قاشق چای‌خوری", 7), MeasurementUnit("لیوان", 320))),

    // غذاهای ایرانی - خورش‌ها
    Food("خورشت قیمه", 150.0, 10.0, 10.0, 8.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("خورشت قورمه سبزی", 135.0, 9.0, 6.0, 8.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("خورشت فسنجان", 260.0, 8.0, 12.0, 20.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("خورشت کرفس", 120.0, 9.0, 5.0, 7.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("خورشت بامیه", 110.0, 8.0, 7.0, 6.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("خورشت آلو اسفناج", 140.0, 8.0, 10.0, 7.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("خورشت قیمه بادمجان", 155.0, 9.0, 9.0, 9.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("قاشق غذاخوری", 25))),

    // پلوها
    Food("لوبیا پلو", 170.0, 6.0, 25.0, 5.0, listOf(MeasurementUnit("کفگیر", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("عدس پلو", 160.0, 6.0, 28.0, 3.0, listOf(MeasurementUnit("کفگیر", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("سبزی پلو", 145.0, 3.0, 28.0, 2.0, listOf(MeasurementUnit("کفگیر", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("باقالی پلو", 155.0, 5.0, 27.0, 2.0, listOf(MeasurementUnit("کفگیر", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("زرشک پلو", 165.0, 3.0, 34.0, 2.0, listOf(MeasurementUnit("کفگیر", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("استانبولی پلو", 150.0, 3.0, 29.0, 3.0, listOf(MeasurementUnit("کفگیر", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("کلم پلو", 145.0, 4.0, 26.0, 3.0, listOf(MeasurementUnit("کفگیر", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("رشته پلو", 170.0, 5.0, 33.0, 2.0, listOf(MeasurementUnit("کفگیر", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("مرصع پلو", 210.0, 4.0, 38.0, 5.0, listOf(MeasurementUnit("کفگیر", 150), MeasurementUnit("قاشق غذاخوری", 25))),
    Food("شیرین پلو", 220.0, 3.0, 45.0, 4.0, listOf(MeasurementUnit("کفگیر", 150), MeasurementUnit("قاشق غذاخوری", 25))),

    // آش و سوپ
    Food("آش رشته", 95.0, 4.0, 14.0, 2.5, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("کاسه متوسط", 300), MeasurementUnit("لیوان", 240))),
    Food("آش جو", 70.0, 3.0, 12.0, 1.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("کاسه متوسط", 300), MeasurementUnit("لیوان", 240))),
    Food("آش دوغ", 80.0, 3.5, 10.0, 2.5, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("کاسه متوسط", 300), MeasurementUnit("لیوان", 240))),
    Food("آش شله قلمکار", 110.0, 6.0, 13.0, 3.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("کاسه متوسط", 300), MeasurementUnit("لیوان", 240))),
    Food("سوپ جو", 55.0, 2.5, 8.0, 1.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("کاسه متوسط", 250), MeasurementUnit("لیوان", 240))),
    Food("سوپ مرغ", 50.0, 4.0, 4.0, 2.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("کاسه متوسط", 250), MeasurementUnit("لیوان", 240))),
    Food("سوپ قارچ", 60.0, 2.5, 6.0, 3.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("کاسه متوسط", 250), MeasurementUnit("لیوان", 240))),

    // خوراک‌ها و غذاهای سنتی
    Food("آبگوشت", 140.0, 9.0, 7.0, 8.0, listOf(MeasurementUnit("ملاقه", 150), MeasurementUnit("کاسه متوسط", 300))),
    Food("گوشت کوبیده", 155.0, 8.0, 10.0, 9.0, listOf(MeasurementUnit("قاشق غذاخوری", 25), MeasurementUnit("پیاله متوسط", 150))),
    Food("کشک بادمجان", 160.0, 6.0, 10.0, 10.0, listOf(MeasurementUnit("قاشق غذاخوری", 25), MeasurementUnit("پرس متوسط", 200))),
    Food("میرزا قاسمی", 120.0, 5.0, 8.0, 7.0, listOf(MeasurementUnit("قاشق غذاخوری", 25), MeasurementUnit("پرس متوسط", 200))),
    Food("کوکو سبزی", 180.0, 7.0, 8.0, 13.0, listOf(MeasurementUnit("عدد متوسط", 50))),
    Food("کوکو سیب زمینی", 200.0, 5.0, 24.0, 9.0, listOf(MeasurementUnit("عدد متوسط", 60))),
    Food("کتلت", 250.0, 12.0, 15.0, 15.0, listOf(MeasurementUnit("عدد متوسط", 50))),
    Food("دلمه برگ مو", 170.0, 5.0, 22.0, 7.0, listOf(MeasurementUnit("عدد", 40))),
    Food("سمبوسه", 300.0, 7.0, 34.0, 15.0, listOf(MeasurementUnit("عدد متوسط", 100))),
    Food("فلافل", 333.0, 13.0, 31.0, 17.0, listOf(MeasurementUnit("عدد", 15))),

    // کباب‌ها
    Food("کباب کوبیده", 250.0, 16.0, 3.0, 19.0, listOf(MeasurementUnit("سیخ", 100))),
    Food("جوجه کباب", 190.0, 27.0, 1.0, 8.0, listOf(MeasurementUnit("سیخ", 150))),
    Food("کباب برگ", 220.0, 26.0, 0.0, 12.0, listOf(MeasurementUnit("سیخ", 150))),
    Food("کباب چنجه", 240.0, 25.0, 1.0, 15.0, listOf(MeasurementUnit("سیخ", 150))),
    Food("کباب ترش", 230.0, 24.0, 4.0, 13.0, listOf(MeasurementUnit("سیخ", 150))),

    // صبحانه و میان‌وعده‌های رایج
    Food("حلیم", 120.0, 6.0, 16.0, 3.0, listOf(MeasurementUnit("کاسه متوسط", 300), MeasurementUnit("قاشق غذاخوری", 25), MeasurementUnit("لیوان", 240))),
    Food("عدسی", 90.0, 6.0, 15.0, 1.0, listOf(MeasurementUnit("کاسه متوسط", 300), MeasurementUnit("ملاقه", 150), MeasurementUnit("لیوان", 240))),
    Food("املت گوجه فرنگی", 130.0, 7.0, 5.0, 9.0, listOf(MeasurementUnit("پرس متوسط", 200), MeasurementUnit("قاشق غذاخوری", 25), MeasurementUnit("لیوان", 240))),
    Food("کله پاچه", 320.0, 18.0, 2.0, 26.0, listOf(MeasurementUnit("یک پرس متوسط", 250))),
)
