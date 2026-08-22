import { useMemo, useState } from 'react';
import './App.css';

const initialStudents = [
    { id: 1, name: 'Alice Johnson', score: 96 },
    { id: 2, name: 'Marcus Lee', score: 84 },
    { id: 3, name: 'Priya Shah', score: 72 },
];

function getLetterGrade(score) {
    if (score >= 90) return 'A';
    if (score >= 80) return 'B';
    if (score >= 70) return 'C';
    if (score >= 60) return 'D';
    return 'F';
}

function App() {
    const [students, setStudents] = useState(initialStudents);
    const [name, setName] = useState('');
    const [score, setScore] = useState('');

    const stats = useMemo(() => {
        if (students.length === 0) {
            return {
                average: 0,
                highest: null,
                lowest: null,
                total: 0,
            };
        }

        const total = students.reduce((sum, student) => sum + Number(student.score), 0);
        const highest = students.reduce((max, student) =>
            Number(student.score) > Number(max.score) ? student : max
        );
        const lowest = students.reduce((min, student) =>
            Number(student.score) < Number(min.score) ? student : min
        );

        return {
            total: students.length,
            average: total / students.length,
            highest,
            lowest,
        };
    }, [students]);

    const handleSubmit = (event) => {
        event.preventDefault();

        const trimmedName = name.trim();
        const numericScore = Number(score);

        if (!trimmedName) {
            alert('Please enter a student name.');
            return;
        }

        if (Number.isNaN(numericScore) || numericScore < 0 || numericScore > 100) {
            alert('Please enter a score between 0 and 100.');
            return;
        }

        setStudents((currentStudents) => [
            ...currentStudents,
            { id: Date.now(), name: trimmedName, score: numericScore },
        ]);
        setName('');
        setScore('');
    };

    const handleRemove = (studentId) => {
        setStudents((currentStudents) =>
            currentStudents.filter((student) => student.id !== studentId)
        );
    };

    return ( <
            div className = "app-container" >
            <
            header className = "header" >
            <
            h1 > Student Grade Tracker < /h1> <
            p > Track student scores, averages, and performance quickly. < /p> < /
            header >

            <
            main className = "content-grid" >
            <
            section className = "panel" >
            <
            h2 > Add Student < /h2> <
            form onSubmit = { handleSubmit }
            className = "student-form" >
            <
            label >
            Student Name <
            input type = "text"
            value = { name }
            onChange = {
                (event) => setName(event.target.value)
            }
            placeholder = "e.g. Emma Davis" /
            >
            <
            /label>

            <
            label >
            Score <
            input type = "number"
            min = "0"
            max = "100"
            step = "0.1"
            value = { score }
            onChange = {
                (event) => setScore(event.target.value)
            }
            placeholder = "0 - 100" /
            >
            <
            /label>

            <
            button type = "submit" > Add Student < /button> < /
            form > <
            /section>

            <
            section className = "panel stats-panel" >
            <
            h2 > Statistics < /h2> <
            div className = "stats-grid" >
            <
            div >
            <
            span > Total Students < /span> <
            strong > { stats.total } < /strong> < /
            div > <
            div >
            <
            span > Average Score < /span> <
            strong > { stats.average.toFixed(2) } < /strong> < /
            div > <
            div >
            <
            span > Highest < /span> <
            strong > { stats.highest ? `${stats.highest.name} (${stats.highest.score})` : '—' } <
            /strong> < /
            div > <
            div >
            <
            span > Lowest < /span> <
            strong > { stats.lowest ? `${stats.lowest.name} (${stats.lowest.score})` : '—' } <
            /strong> < /
            div > <
            /div> < /
            section > <
            /main>

            <
            section className = "panel table-panel" >
            <
            h2 > Student Summary < /h2> {
            students.length === 0 ? ( <
                p className = "empty-state" > No student records yet. < /p>
            ) : ( <
                table >
                <
                thead >
                <
                tr >
                <
                th > # < /th> <
                th > Name < /th> <
                th > Score < /th> <
                th > Grade < /th> <
                th > Action < /th> < /
                tr > <
                /thead> <
                tbody > {
                    students.map((student, index) => ( <
                        tr key = { student.id } >
                        <
                        td > { index + 1 } < /td> <
                        td > { student.name } < /td> <
                        td > { student.score } < /td> <
                        td > { getLetterGrade(student.score) } < /td> <
                        td >
                        <
                        button className = "remove-button"
                        type = "button"
                        onClick = {
                            () => handleRemove(student.id)
                        } >
                        Remove <
                        /button> < /
                        td > <
                        /tr>
                    ))
                } <
                /tbody> < /
                table >
            )
        } <
        /section> < /
        div >
);
}

export default App;