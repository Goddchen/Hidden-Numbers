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
                        "repdigit": {
                            "name": "Repdigit",
                            "description": "This number constists only of intances of the same digit"
                        },
                        "palindrome": {
                            "name": "Palindrome",
                            "description": "This number read forwards the same as backwards"
                        },
                        "multiple-of-42": {
                            "name": "Multiple of 42",
                            "description": "This is a multiple of 42, the answer to the ultimate question of live, the universe, and everything",
                            "url": "https://en.wikipedia.org/wiki/Phrases_from_The_Hitchhiker%27s_Guide_to_the_Galaxy#Answer_to_the_Ultimate_Question_of_Life,_the_Universe,_and_Everything_(42)"
                        },
                        "meta": {
                            "digitsum": "Digit Sum",
                            "dayofyear": "Day of the year",
                            "remainingdaysofyear": "Remaining days of year",
                            "origin": "Origin",
                            "reason": "Reason",
                            "dayofmonth": "Day of month"
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
                            "description": "Freitag der 13.",
                            "url": "https://de.wikipedia.org/wiki/Freitag,_der_13."
                        },
                        "repdigit": {
                            "name": "Schnapszahl",
                            "description": "Diese Zahl enthält nur gleiche Ziffern"
                        },
                        "palindrome": {
                            "name": "Palindrom",
                            "description": "Diese Zahl liest sich vorwärts gleich wie rückwärts"
                        },
                        "multiple-of-42": {
                            "name": "Vielfaches von 42",
                            "description": "Ein Vielfaches von 42, der Antwort nach dem Leben, dem Universum und dem ganzen Rest",
                            "url": "https://de.wikipedia.org/wiki/42_(Antwort)"
                        },
                        "meta": {
                            "digitsum": "Quersumme",
                            "dayofyear": "Tag des Jahres",
                            "remainingdaysofyear": "Verbleibende Tage im Jahr",
                            "origin": "Herkunft",
                            "reason": "Grund",
                            "dayofmonth": "Tag des Monats"
                        }
                    }
                }
            }
        },
        fallbackLng: 'en',
        debug: true
    })

export default i18n;