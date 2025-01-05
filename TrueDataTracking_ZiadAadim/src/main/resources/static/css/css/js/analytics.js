document.addEventListener("DOMContentLoaded", () => {
    // Contexts for the charts
    const navigationCtx = document.getElementById("navigationPathChart").getContext("2d");
    const timeSpentCtx = document.getElementById("timeSpentChart").getContext("2d");
    const goBackRatesCtx = document.getElementById("goBackRatesChart").getContext("2d");

    // Navigation Path Chart
    const loadNavigationChart = () => {
        fetch("/api/analytics/navigation-path")
            .then(response => response.json())
            .then(data => {
                const labels = Object.keys(data); // Pages like Anatomy, Gesture, Perspective
                const values = Object.values(data); // Count of first navigations

                new Chart(navigationCtx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'First Navigation from Home',
                            data: values,
                            backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                            borderColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            legend: {
                                display: false // Disable legend for simplicity
                            }
                        },
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            })
            .catch(error => console.error("Error fetching navigation path data:", error));
    };

    // Time Spent Chart
    const loadTimeSpentChart = () => {
        fetch("/api/analytics/time-spent")
            .then(response => response.json())
            .then(data => {
                const labels = Object.keys(data); // Pages
                const timeSpentData = Object.values(data); // Time spent on each page

                new Chart(timeSpentCtx, {
                    type: 'pie',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'Time Spent',
                            data: timeSpentData,
                            backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0']
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                position: 'top',
                            },
                            tooltip: {
                                callbacks: {
                                    label: (context) => {
                                        const value = context.raw;
                                        return `${context.label}: ${value} seconds`;
                                    }
                                }
                            }
                        },
                    }
                });
            })
            .catch(error => console.error("Error fetching time spent data:", error));
    };

    // Go Back Rates Chart
    const loadGoBackRatesChart = () => {
        fetch("/api/analytics/go-back-rates")
            .then(response => response.json())
            .then(data => {
                const labels = Object.keys(data); // Pages
                const values = Object.values(data); // Go-back actions

                new Chart(goBackRatesCtx, {
                    type: 'line',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'Go Back Actions',
                            data: values,
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            legend: {
                                position: 'top',
                            },
                            tooltip: {
                                callbacks: {
                                    label: (context) => {
                                        const value = context.raw;
                                        return `${context.label}: ${value} times`;
                                    }
                                }
                            }
                        },
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            })
            .catch(error => console.error("Error fetching go-back rates data:", error));
    };

    // Button to clear the database
    const clearDataBtn = document.getElementById("clear-data-btn");
    clearDataBtn.addEventListener("click", () => {
        if (confirm("Are you sure you want to delete all data?")) {
            fetch("/api/analytics/clear", { method: "DELETE" })
                .then(response => response.text())
                .then(message => {
                    alert(message);
                    loadUserStats(); // Refresh user stats after clearing
                })
                .catch(error => console.error("Error clearing data:", error));
        }
    });

    // Load user statistics
    const loadUserStats = () => {
        fetch("/api/analytics/user-stats")
            .then(response => response.json())
            .then(data => {
                document.getElementById("user-count").textContent = data.userCount;
                const userList = document.getElementById("user-list");
                userList.innerHTML = ""; // Clear existing list
                data.userIds.forEach(userId => {
                    const li = document.createElement("li");
                    li.textContent = userId;
                    userList.appendChild(li);
                });
            })
            .catch(error => console.error("Error fetching user stats:", error));
    };

    // Initial loads for charts and user stats
    loadNavigationChart();
    loadTimeSpentChart();
    loadGoBackRatesChart();
    loadUserStats();

    const userList = document.getElementById("user-list");
    const modal = document.getElementById("user-details-modal");
    const closeModalBtn = document.getElementById("close-modal-btn");

    const loadUserDetails = (userId) => {
        document.getElementById("user-navigation-path").previousElementSibling.textContent = `User ID: ${userId}`;
        fetch(`/api/analytics/user/${userId}/details`)
            .then((response) => response.json())
            .then((data) => {
                document.getElementById("user-navigation-path").textContent = ["Home", ...data.navigationPath].join(" -> ");
                document.getElementById("time-spent-anatomy").textContent = data.timeSpent.Anatomy || 0;
                document.getElementById("time-spent-gesture").textContent = data.timeSpent.Gesture || 0;
                document.getElementById("time-spent-perspective").textContent = data.timeSpent.Perspective || 0;
                document.getElementById("most-time-spent").textContent = data.mostTimeSpent || "N/A";
                modal.style.display = "flex"; // Show the modal
            })
            .catch((error) => console.error("Error fetching user details:", error));
    };

    userList.addEventListener("click", (event) => {
        if (event.target.tagName === "LI") {
            const userId = event.target.textContent;
            loadUserDetails(userId);
        }
    });

    closeModalBtn.addEventListener("click", () => {
        modal.style.display = "none"; // Hide the modal
    });
});




