import React, { useState } from 'react';
import { Paper } from '@mui/material';
import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

const Form = () => {
    const paperStyle = { padding: '20px 20px', width: 600, margin: '20px auto' };
    const paperStyleSequence = { padding: '10px 10px', width: 300 };
    //elements are sent to server side
    const [elements, setElements] = useState('');
    // data retrieved from server side
    const [fibonacciData, setFibonacciData] = useState({});
    // check for errors in the input field
    const [error, setError] = useState('');

    // update input values and performs validation
    // clears error message and generated sequences when text field is empty
    const handleTextBox = (e) => {
        const value = e.target.value;
        setElements(value);

        // validate input
        const isValid = /^[1-9][0-9]?$|^100$/.test(value);
        setError(isValid ? '' : 'Please enter a number between 1 and 100');

        // clear data when text box is empty
        if (value === '') {
            setError('');
            setFibonacciData({});
        }
    };

    // sends elements to the server side and generates the fibonacci sequences
    const sendElements = () => {
        fetch(`http://localhost:8080/fibonacci?elements=${elements}`)
            .then((res) => res.json())
            .then((data) => {
                setFibonacciData(data);
            })
            .catch((error) => {
                console.error('An error occurred during the request:', error);
            });
    };

    // clears all data on the page
    const handleClear = () => {
        setElements('');
        setFibonacciData({});
        setError('');
    };

    // disables generate button
    const isGenerateDisabled = !elements || Boolean(error);

    return (
        // instructions and text box to enter number
        <div>
            <Paper elevation={1} style={paperStyle}>
                <h2>Welcome to the Fibonacci Sequence Generator!</h2>
                <div style={{ textAlign: 'left' }}>
                    <p>
                        Two sequences will be generated based on the number entered. The number determines the number of Fibonacci elements that will be
                        generated.
                    </p>
                    <ol>
                        <li>Original Fibonacci Sequence</li>
                        <li>
                            Sorted Fibonacci Sequence:
                            <br />
                            Even numbers will be generated first in descending order, followed by odd numbers in descending order.
                        </li>
                    </ol>
                </div>
                <Box
                    component="form"
                    sx={{
                        '& > :not(style)': { m: 1, width: '40ch' },
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <TextField
                        id="standard-basic"
                        label="Please enter a number between 1 and 100"
                        variant="standard"
                        value={elements}
                        onChange={handleTextBox}
                        error={Boolean(error)}
                        helperText={error}
                    />
                </Box>
                <Stack spacing={2} direction="row" justifyContent="center">
                    <Button variant="contained" color="primary" onClick={sendElements} disabled={isGenerateDisabled}>
                        Generate
                    </Button>
                    <Button variant="contained" color="secondary" onClick={handleClear}>
                        Clear
                    </Button>
                </Stack>
            </Paper>
            {fibonacciData.fibonacciList && fibonacciData.sortedFibonacciList && (
                // Prints the fibonacci sequences
                <div style={{ display: 'flex', flexDirection: 'row', justifyContent: 'center' }}>
                    <Paper elevation={3} style={paperStyleSequence}>
                        <h3>Original Fibonacci Sequence:</h3>
                        <ol>
                            {fibonacciData.fibonacciList.map((num, index) => (
                                <li key={`fibonacci_${index}`}>{num}</li>
                            ))}
                        </ol>
                    </Paper>

                    <Paper elevation={3} style={paperStyleSequence}>
                        <h3>Sorted Fibonacci Sequence:</h3>
                        <ol>
                            {fibonacciData.sortedFibonacciList.map((num, index) => (
                                <li key={`sorted_${index}`}>{num}</li>
                            ))}
                        </ol>
                    </Paper>
                </div>
            )}
        </div>
    );
};

export default Form;
