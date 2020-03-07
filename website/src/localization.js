import i18n from 'i18next'
import { initReactI18next } from 'react-i18next'
import LanguageDetector from 'i18next-browser-languagedetector'

i18n
    .use(LanguageDetector)
    .use(initReactI18next)
    .init({
        resources: {
            en: {
                translation: {
                    "seeker": {
                        "prime": {
                            "name": "Prime number",
                            "description": "This is a prime number"
                        },
                        "equal": {
                            "name": "Equal number",
                            "description": "This is an equal number"
                        },
                        "odd": {
                            "name": "Odd number",
                            "description": "This is an odd number"
                        },
                        "unlucky-number": {
                            "name": "Unlucky number",
                            "description": "This is an unlucky number in some parts of the world"
                        },
                        "friday-13th": {
                            "name": "Friday 13th",
                            "description": "Friday 13th"
                        },
                        "meta": {
                            "digitsum": "Digit Sum",
                            "dayofyear": "Day of the year",
                            "remainingdaysofyear": "Remaining days of year",
                            "origin": "Origin",
                            "reason": "Reason"
                        }
                    }
                }
            },
            de: {
                translation: {
                    "seeker": {
                        "prime": {
                            "name": "Primzahl",
                            "description": "Das ist eine Primzahl"
                        },
                        "equal": {
                            "name": "Gerade Zahl",
                            "description": "Das ist eine gerade Zahl"
                        },
                        "odd": {
                            "name": "Ungerade Zahl",
                            "description": "Das ist eine ungerade Zahl"
                        },
                        "unlucky-number": {
                            "name": "Unglückszahl",
                            "description": "In einigen Bereichen der Erde ist dies eine Unglückszahl"
                        },
                        "friday-13th": {
                            "name": "Freitag der 13.",
                            "description": "Freitag der 13."
                        },
                        "meta": {
                            "digitsum": "Quersumme",
                            "dayofyear": "Tag des Jahres",
                            "remainingdaysofyear": "Verbleibende Tage im Jahr",
                            "origin": "Herkunft",
                            "reason": "Grund"
                        }
                    }
                }
            }
        },
        fallbackLng: 'en',
        debug: true
    })

export default i18n;