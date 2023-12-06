import React, { useLayoutEffect } from 'react';
import { Instance } from '../util/Instance/Instance';
import axios from 'axios';

export default function User() {
  const token = localStorage.getItem('jwtToken');
  useLayoutEffect(() => {
    console.log(token);
    axios
      .get('/backend/api/v1/user', {
        headers: {
          Authorization: token,
        },
      })
      .then((response) => {
        console.log('response: ' + response.status);
        if (response) {
          if (response.status === 200) {
            // Handle the case when the response is successful
            // You might want to update the state or perform some other action here
          }
        } else {
          alert('로그인이 필요한 서비스입니다.');
          window.location.href = '/';
        }
      })
      .catch((error) => {
        console.error('Error:', error);
        alert('로그인이 필요한 서비스입니다.');
        window.location.href = '/';
        // Handle errors, e.g., show an error message to the user
      });
  }, [token]);

  return (
    <>
      <div>사용자페이지입니다.</div>
    </>
  );
}
