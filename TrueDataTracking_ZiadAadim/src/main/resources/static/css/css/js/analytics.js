console.log('test')


document.addEventListener("DOMContentLoaded", () => {
    // Example Chart.js setup
    const navigationCtx = document.getElementById("navigationPathChart").getContext("2d");
    const timeSpentCtx = document.getElementById("timeSpentChart").getContext("2d");
    const goBackRatesCtx = document.getElementById("goBackRatesChart").getContext("2d");

    // Navigation Path Chart
    new Chart(navigationCtx, {
        type: 'bar',
        data: {
            labels: ['Main page', 'Anatomy', 'Gesture', 'Perspective'],
            datasets: [{
                label: 'Navigation Path Frequency',
                data: [10, 5, 8, 4],
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
        }
    });

    // Time Spent Chart
    new Chart(timeSpentCtx, {
        type: 'pie',
        data: {
            labels: ['Main page', 'Anatomy', 'Gesture', 'Perspective'],
            datasets: [{
                label: 'Time Spent',
                data: [150, 50, 80, 30],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0']
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false, // Disable maintaining the aspect ratio
            plugins: {
                legend: {
                    position: 'top',
                }
            },
            layout: {
                padding: {
                    top: 10,
                    bottom: 10,
                }
            }
        }
    });

    // Go Back Rates Chart
    new Chart(goBackRatesCtx, {
        type: 'line',
        data: {
            labels: ['Anatomy', 'Gesture', 'Perspective'],
            datasets: [{
                label: 'Go Back Actions',
                data: [3, 4, 2],
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgba(255, 99, 132, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
        }
    });
});
