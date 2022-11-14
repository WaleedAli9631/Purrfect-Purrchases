
        // Import the functions you need from the SDKs you need
        import { initializeApp } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-app.js";
        import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-analytics.js";
        import { getAuth } from "https://www.gstatic.com/firebasejs/9.14.0/firebase-auth.js";

        // TODO: Add SDKs for Firebase products that you want to use
        // https://firebase.google.com/docs/web/setup#available-libraries

        // Your web app's Firebase configuration
        // For Firebase JS SDK v7.20.0 and later, measurementId is optional
        const firebaseConfig = {
            apiKey: "AIzaSyBLX7uYmN-fi4PrDoF1d-etyL9pbCdDhDk",
            authDomain: "purrfect-purrchases.firebaseapp.com",
            projectId: "purrfect-purrchases",
            storageBucket: "purrfect-purrchases.appspot.com",
            messagingSenderId: "877568370203",
            appId: "1:877568370203:web:92c8488af9a0029ac67abe",
            measurementId: "G-DTVS5KJ978"
        };

        // Initialize Firebase
        const app = initializeApp(firebaseConfig);
        const analytics = getAnalytics(app);

        const auth = getAuth(app);