import { fireEvent, render, screen } from '@testing-library/react';
import App from './App';

test('renders the tracker and adds a student', () => {
    render( < App / > );

    expect(screen.getByRole('heading', { name: 'Student Grade Tracker' })).toBeInTheDocument();

    fireEvent.change(screen.getByPlaceholderText('e.g. Emma Davis'), {
        target: { value: 'Jordan Smith' },
    });
    fireEvent.change(screen.getByPlaceholderText('0 - 100'), {
        target: { value: '88' },
    });
    fireEvent.click(screen.getByRole('button', { name: 'Add Student' }));

    const studentRow = screen.getByText('Jordan Smith').closest('tr');
    expect(studentRow).toHaveTextContent('88');
    expect(studentRow).toHaveTextContent('B');
});