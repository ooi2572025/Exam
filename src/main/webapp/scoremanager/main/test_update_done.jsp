<%-- 成績変更完了画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績変更完了
            </h2>

            <div class="mx-3 mb-4">
                <p class="fs-5">変更が完了しました。</p>

               
                <form action="TestRegist.action" method="get">
                    <input type="hidden" name="f1" value="${f1}">
                    <input type="hidden" name="f2" value="${f2}">
                    <input type="hidden" name="f3" value="${f3}">
                    <input type="hidden" name="f4" value="${f4}">

                    <button type="submit" class="btn btn-secondary">
                        成績一覧に戻る
                    </button>
                </form>
            </div>

        </section>
    </c:param>
</c:import>