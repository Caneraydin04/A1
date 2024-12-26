import time
from apscheduler.schedulers.background import BackgroundScheduler
from gtts import gTTS
import datetime as datetime



class Benjamin:
    def __init__(self):
        self.scheduler = BackgroundScheduler()
        self.morning_message = "Günaydın! Bugün harika şeyler başarabilirsin. Unutma, her küçük adım seni hedeflerine yaklaştırır!"
        self.plan_reminder_times = {"sabah": "09:00", "gece": "23:55"}
        self.work_sessions = 80 * 60  
        self.last_reminder_time = None
        self.start_scheduler()

    def send_morning_motivation(self):
        print(self.morning_message)
        self.text_to_speech(self.morning_message)

    def plan_check_reminder(self, time_of_day):
        message = f"Merhaba! Bu bir {time_of_day} plan kontrol hatırlatmasıdır."
        print(message)
        self.text_to_speech(message)

    def send_break_reminder(self):
        message = "Harika çalışıyorsun! Şimdi kısa bir mola vermenin tam zamanı."
        print(message)
        self.text_to_speech(message)

    def text_to_speech(self, text):
        engine = gTTS.init()
        engine.setProperty("rate", 150)  # Konuşma hızı
        engine.setProperty("volume", 0.9)  # Ses seviyesi
        engine.say(text)
        engine.runAndWait()

    def start_scheduler(self):
        # Sabah motivasyon mesajı
        self.scheduler.add_job(self.send_morning_motivation, 'cron', hour=9, minute=0)

        # Planlama hatırlatmaları
        for time_of_day, time_str in self.plan_reminder_times.items():
            hour, minute = map(int, time_str.split(":"))
            self.scheduler.add_job(self.plan_check_reminder, 'cron', hour=hour, minute=minute, args=[time_of_day])

        # Ders molası hatırlatmaları (80 dakikada bir)
        self.scheduler.add_job(self.send_break_reminder, 'interval', seconds=self.work_sessions)

        self.scheduler.start()

    def stop_scheduler(self):
        self.scheduler.shutdown()


if __name__ == "__main__":
    assistant = Benjamin()
    print("Benjamin çalışmaya başladı! Görevler planlandı.")
    try:
        while True:
            time.sleep(1)
    except (KeyboardInterrupt, SystemExit):
        assistant.stop_scheduler()
        print("Benjamin durduruldu.")
