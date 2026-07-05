import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

test('renders the app version footer', () => {
  const { getByText } = render(<App />);
  const versionElement = getByText(/Version:/i);
  expect(versionElement).toBeInTheDocument();
});
